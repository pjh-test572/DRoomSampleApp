package com.sample.pjh.gitusersearch.view.fragment

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager.widget.ViewPager
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.common.listener.OnGitUserViewListener
import com.sample.pjh.gitusersearch.common.util.CustomLog
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.data.viewmodel.GitUserSearchViewModel
import com.sample.pjh.gitusersearch.databinding.FragmentGitusersearchBinding
import com.sample.pjh.gitusersearch.view.adapter.GitUserSearchAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class GitUserSearchFragment : BaseFragment<FragmentGitusersearchBinding>() , OnGitUserViewListener<ArrayList<UserModel>>, ViewPager.OnPageChangeListener {

    private lateinit var mViewModel: GitUserSearchViewModel

    override val baseTag: String = this@GitUserSearchFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_gitusersearch
    lateinit var db : Db


    override fun init() {
        db = Db.getInstance(this.requireContext())!!
        mDisposable = CompositeDisposable()
        mViewModel = ViewModelProvider(this, viewModelFactory).get(GitUserSearchViewModel::class.java).apply {
            mDisposable = this@GitUserSearchFragment.mDisposable
            db = Db.getInstance(this@GitUserSearchFragment.requireContext())!!
            listener = this@GitUserSearchFragment
        }
        mBinding.viewModel = mViewModel

        if (mBinding.recyclerview.itemAnimator is SimpleItemAnimator)
            (mBinding.recyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        mBinding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mBinding.recyclerview.adapter != null && !mViewModel.isLoading && mViewModel.totalPage > mViewModel.page &&
                    (mBinding.recyclerview.adapter as GitUserSearchAdapter).itemCount - (mBinding.recyclerview.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() <= Info.PAGE_PER_ITEM/2 ) {
                    mViewModel.isLoading = true
                    mViewModel.getSearchUser()
                }
            }
        })

        mDisposable.add(Observable.create<CharSequence> { emitter ->
            mBinding.edittext.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) { }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let {
                        if(!TextUtils.isEmpty(it.toString()) && it.toString().isNotEmpty()) mViewModel.gitUserSearchClear.set(true)
                        else mViewModel.gitUserSearchClear.set(false)

                        emitter.onNext(it)
                    }
                }
            })
        }.debounce(1000L, TimeUnit.MILLISECONDS)
            .filter { text -> text.length > 1  }
            .observeOn(AndroidSchedulers.mainThread()) //Toast must be running on UI Thread
            .subscribe {
                if(!mViewModel.isLoading) setSearch(it.toString())
            }
        )

    }


    override fun onNext(it: ArrayList<UserModel>) {
        var index = 0
        if(mBinding.recyclerview.adapter == null){
            mBinding.recyclerview.adapter = GitUserSearchAdapter().apply {
                mContext = this@GitUserSearchFragment.requireContext()
                listener = this@GitUserSearchFragment
                mDisposable = this@GitUserSearchFragment.mDisposable
                db = this@GitUserSearchFragment.db
                setList(it)
            }
        }else {
            index = (mBinding.recyclerview.adapter as GitUserSearchAdapter).itemCount
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).mList.addAll(it)
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyItemRangeInserted(index, (mBinding.recyclerview.adapter as GitUserSearchAdapter).itemCount)
        }

        mViewModel.isLoading = false
        if((mBinding.recyclerview.adapter as GitUserSearchAdapter).itemCount > 0) mViewModel.emptyViewVisible.set(false)
        else mViewModel.emptyViewVisible.set(true)
    }

    override fun onClick(type: Int, position : Int, value: Any?) {
        when(type){
            R.id.imageview_user_fav->{
                var user = value as UserModel

                if(user.isFav){
                    mDisposable.add(Observable.just(user).subscribeOn(Schedulers.io()).subscribe {
                        db.gitUserDao().delete(it.id)
                    })
                }else{
                    mDisposable.add(Observable.just(user).subscribeOn(Schedulers.io()).subscribe {
                        db.gitUserDao().delete(it.id)
                        db.gitUserDao().insert(it.gitUserEntityData())
                    })
                }
                (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyItemChanged(position)
            }
            R.id.constraintLayout->{
                var user = value as UserModel
                mDisposable.add(GitServer.getUserInfo(user = user.login,
                    listener = ServerResponseCallback(nextTask = {
                        if(CustomLog.flag)CustomLog.L("onClick","ServerResponseCallback nextTask",it)
                    },completeTask = {

                    },failedTask = { e, code ->

                    }))
                )
            }
            R.id.imageview_search ->{
                setSearch(mBinding.edittext.text.toString())
            }
            R.id.imageview_clear ->{
                mBinding.edittext.text = Editable.Factory.getInstance().newEditable("")
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        try{
            Db.destroyInstance()
        }catch (e : Exception){

        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

    override fun onPageSelected(position: Int) {
        if(mBinding.recyclerview.adapter != null){
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyDataSetChanged()
        }
    }

    private fun setSearch(txt : String){
        if(!TextUtils.isEmpty(txt)){
            mViewModel.isLoading = true
            if(mBinding.recyclerview.adapter != null){
                (mBinding.recyclerview.adapter as GitUserSearchAdapter).mList.clear()
                (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyDataSetChanged()
            }
            mViewModel.searchTxt = txt
            mViewModel.setInitPage()
            mViewModel.getSearchUser()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): GitUserSearchFragment {
            return GitUserSearchFragment()
        }
    }
}