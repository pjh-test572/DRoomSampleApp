package com.sample.pjh.gitusersearch.view.fragment

import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.common.type.BuildType
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.viewmodel.GitInfoRepoViewModel
import com.sample.pjh.gitusersearch.data.viewmodel.GitInfoStarViewModel
import com.sample.pjh.gitusersearch.data.viewmodel.UserInfoViewModel
import com.sample.pjh.gitusersearch.databinding.FragmentUserinfoBinding
import com.sample.pjh.gitusersearch.view.activity.base.BaseActivity
import com.sample.pjh.gitusersearch.view.adapter.RepoListAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable

class UserInfoFragment : BaseFragment<FragmentUserinfoBinding>() , OnViewModelBaseListener {

    // -------- LOCAL VALUE --------
    lateinit var mViewModel : UserInfoViewModel
    lateinit var mViewModelRepo : GitInfoRepoViewModel
    lateinit var mViewModelStar : GitInfoStarViewModel
    // -----------------------------

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    override val baseTag: String = this@UserInfoFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_userinfo

    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init() {
        mDisposable = CompositeDisposable()
        mViewModel = UserInfoViewModel().apply {
            this@apply.mDisposable = this@UserInfoFragment.mDisposable
            db = Db.getInstance(this@UserInfoFragment.requireContext())!!

            userLogin = arguments!!.getString("user_login") ?: ""
            if(TextUtils.isEmpty(userLogin)) (requireContext() as BaseActivity).finish()
        }

        mViewModelRepo = ViewModelProvider(this, viewModelFactory).get(GitInfoRepoViewModel::class.java).apply {
            mDisposable = this@UserInfoFragment.mDisposable
            userLogin = this@UserInfoFragment.mViewModel.userLogin
        }

        mViewModelStar = ViewModelProvider(this, viewModelFactory).get(GitInfoStarViewModel::class.java).apply {
            mDisposable = this@UserInfoFragment.mDisposable
            userLogin = this@UserInfoFragment.mViewModel.userLogin
        }

        mBinding.viewModel = mViewModel
        mBinding.includeUserinfoContent.viewModelRepo = mViewModelRepo
        mBinding.includeUserinfoContent.viewModelStar = mViewModelStar

        when(Info.BUILD_TYPE){
            BuildType.NAV->{
                (requireContext() as BaseActivity).supportActionBar?.title = mViewModel.userLogin
                mBinding.toolbarLayout.layoutParams.apply {
                    (this as AppBarLayout.LayoutParams).scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED)
                }
            }
            else ->{
                (requireContext() as BaseActivity).setSupportActionBar(mBinding.toolbarheader)
                mBinding.toolbarLayout.title = mViewModel.userLogin
            }
        }

        getUserInfo()
        getUserRepoList()
        mViewModelRepo.emptyViewVisible.set(false)
        mViewModelStar.emptyViewVisible.set(false)
        mBinding.toolbarLayout.title = resources.getString(R.string.info_tab1)

        mBinding.setClickListener {
            (mBinding.includeUserinfoContent.recyclerview.adapter as RepoListAdapter).mList.clear()
            (mBinding.includeUserinfoContent.recyclerview.adapter as RepoListAdapter).notifyDataSetChanged()
            when(mViewModel.userIsFavChecked.get()){
                true->{
                    mBinding.toolbarLayout.title = resources.getString(R.string.info_tab1)
                    mViewModel.userIsFavChecked.set(false)
                    getUserRepoList()
                }
                false -> {
                    mBinding.toolbarLayout.title = "${resources.getString(R.string.info_tab2)} ${resources.getString(R.string.info_tab1)}"
                    mViewModel.userIsFavChecked.set(true)
                    getUserStarList()
                }
            }
        }

    }

    override fun onClick(type: Int, position: Int, value: Any?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        Db.destroyInstance()
    }

    ////////////////////////////////////////////////


    private fun getUserRepoList(){
        mViewModelRepo.getUserRepoList()
        mViewModelRepo.mUserRepos.observe(this, Observer {
            if(it.size == 0 )mViewModelRepo.emptyViewVisible.set(true)
            else mViewModelRepo.emptyViewVisible.set(false)
            mViewModelStar.emptyViewVisible.set(false)
            mBinding.includeUserinfoContent.recyclerview.adapter = RepoListAdapter().apply {
                mContext = requireContext()
                listener = this@UserInfoFragment
                setList(it)
            }
        })
    }


    private fun getUserStarList(){
        mViewModelStar.getUserStarList()
        mViewModelStar.mUserStar.observe(this, Observer {
            if(it.size == 0 )mViewModelStar.emptyViewVisible.set(true)
            else mViewModelStar.emptyViewVisible.set(false)
            mViewModelRepo.emptyViewVisible.set(false)
            mBinding.includeUserinfoContent.recyclerview.adapter = RepoListAdapter().apply {
                mContext = requireContext()
                listener = this@UserInfoFragment
                setList(it)
            }
        })
    }


    private fun getUserInfo(){
        mViewModel.getUserInfo()
        mViewModel.mUserInfo.observe(this, Observer {
            mBinding.includeUserinfoHeader.imageviewUserAvatar.setImageURI(it.avatar_url)
            mBinding.includeUserinfoHeader.item = it
            mBinding.includeUserinfoHeader.executePendingBindings()
        })
    }




}