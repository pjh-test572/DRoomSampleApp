package com.sample.pjh.gitusersearch.view.fragment

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager.widget.ViewPager
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.common.listener.OnGitUserViewListener
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.common.type.BuildType
import com.sample.pjh.gitusersearch.common.util.CustomIntent
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.viewmodel.GitUserFavoriteViewModel
import com.sample.pjh.gitusersearch.databinding.FragmentGituserfavoriteBinding
import com.sample.pjh.gitusersearch.view.adapter.GitUserSearchAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GitUserFavoriteFragment : BaseFragment<FragmentGituserfavoriteBinding>(), OnGitUserViewListener<ArrayList<UserModel>> ,ViewPager.OnPageChangeListener{

    private lateinit var mViewModel: GitUserFavoriteViewModel
    lateinit var db : Db

    override val baseTag: String = this@GitUserFavoriteFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_gituserfavorite


    override fun init() {
        mLoadingIndicatorUtil = LoadingIndicatorUtil(requireContext())
        db = Db.getInstance(this.requireContext())!!
        mDisposable = CompositeDisposable()
        mViewModel = ViewModelProvider(this, viewModelFactory).get(GitUserFavoriteViewModel::class.java).apply {
            mDisposable = this@GitUserFavoriteFragment.mDisposable
            db = Db.getInstance(this@GitUserFavoriteFragment.requireContext())!!
            listener = this@GitUserFavoriteFragment
        }
        mBinding.viewModel = mViewModel
        when(Info.BUILD_TYPE){
            BuildType.NAV-> mViewModel.getFavUser()
        }
    }


    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }
    override fun onPageSelected(position: Int) {
        if(mBinding.recyclerview.adapter != null){
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).mList.clear()
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyDataSetChanged()
        }
        mViewModel.getFavUser()
    }


    override fun onNext(it: ArrayList<UserModel>) {
        if(mBinding.recyclerview.adapter == null){
            if (mBinding.recyclerview.itemAnimator is SimpleItemAnimator)
                (mBinding.recyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

            mBinding.recyclerview.adapter = GitUserSearchAdapter().apply {
                mContext = this@GitUserFavoriteFragment.requireContext()
                listener = this@GitUserFavoriteFragment
                mDisposable = this@GitUserFavoriteFragment.mDisposable
                db = this@GitUserFavoriteFragment.db
                setList(it)
            }
        }else {
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).mList.addAll(it)
            (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyDataSetChanged()
        }
    }

    override fun onClick(type: Int, position : Int, value: Any?) {
        when(type) {
            R.id.imageview_user_fav -> {
                var user = value as UserModel
                if (user.isFav) {
                    mDisposable.add(Observable.just(user).subscribeOn(Schedulers.io()).subscribe {
                        db.gitUserDao().delete(it.id)
                    })
                } else {
                    mDisposable.add(Observable.just(user).subscribeOn(Schedulers.io()).subscribe {
                        db.gitUserDao().delete(it.id)
                        db.gitUserDao().insert(it.gitUserEntityData())
                    })
                }
                (mBinding.recyclerview.adapter as GitUserSearchAdapter).notifyItemChanged(position)
            }
            R.id.constraintLayout->{
                var user = value as UserModel
                when(Info.BUILD_TYPE){
                    BuildType.NAV->{
                        var bundle = bundleOf("user_login" to user.login)
                        findNavController().navigate(R.id.action_gitUserFavoriteFragment_to_userInfoFragment3, bundle)
                    }
                    else-> CustomIntent.startIntent(requireActivity(), ActType.USER_INFO, "USER_LOGIN",user.login)
                }
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

    companion object {
        @JvmStatic
        fun newInstance(): GitUserFavoriteFragment {
            return GitUserFavoriteFragment()
        }
    }

}