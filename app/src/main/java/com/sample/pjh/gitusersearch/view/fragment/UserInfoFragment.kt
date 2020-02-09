package com.sample.pjh.gitusersearch.view.fragment

import android.text.TextUtils
import androidx.lifecycle.Observer
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.util.CustomLog
import com.sample.pjh.gitusersearch.data.viewmodel.UserInfoViewModel
import com.sample.pjh.gitusersearch.databinding.FragmentUserinfoBinding
import com.sample.pjh.gitusersearch.view.activity.base.BaseActivity
import com.sample.pjh.gitusersearch.view.adapter.SectionsPagerAdapter
import com.sample.pjh.gitusersearch.view.adapter.UserInfoPageAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_scrolling.view.*

class UserInfoFragment : BaseFragment<FragmentUserinfoBinding>() {

    // -------- LOCAL VALUE --------
    lateinit var mViewModel : UserInfoViewModel
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
            userLogin = arguments!!.getString("user.login") ?: "JakeWharton"
            if(CustomLog.flag) CustomLog.L("UserInfoFragment","userLogin",userLogin)
            if(TextUtils.isEmpty(userLogin)) (requireContext() as BaseActivity).finish()
        }
        mBinding.viewModel = mViewModel

        (requireContext() as BaseActivity).setSupportActionBar(mBinding.toolbar)
        mBinding.toolbarLayout.title = mViewModel.userLogin

        getUserInfo()

        val sectionsPagerAdapter = UserInfoPageAdapter(requireContext(), (requireContext() as BaseActivity).supportFragmentManager).apply {
            this@apply.userLogin = this@UserInfoFragment.mViewModel.userLogin
        }
        mBinding.includeUserinfoContent.view_pager.adapter = sectionsPagerAdapter
        mBinding.includeUserinfoContent.tabs.setupWithViewPager(mBinding.includeUserinfoContent.view_pager)

    }


    ////////////////////////////////////////////////


    private fun getUserInfo(){
        mViewModel.getUserInfo()
        mViewModel.mUserInfo.observe(this, Observer {
            if(CustomLog.flag) CustomLog.L("UserInfoActivity","observe",it)
            mBinding.includeUserinfoHeader.imageviewUserAvatar.setImageURI(it.avatar_url)
            mBinding.includeUserinfoHeader.item = it
            mBinding.includeUserinfoHeader.executePendingBindings()
        })
    }



}