package com.sample.pjh.gitusersearch.view.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.common.util.CustomLog
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.viewmodel.UserInfoViewModel
import com.sample.pjh.gitusersearch.databinding.ActivityUserinfoBinding
import com.sample.pjh.gitusersearch.view.activity.base.BaseActivity
import com.sample.pjh.gitusersearch.view.activity.base.BindActivity
import com.sample.pjh.gitusersearch.view.adapter.RepoListAdapter
import com.sample.pjh.gitusersearch.view.adapter.UserInfoPageAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_scrolling.view.*

class UserInfoActivity : BindActivity<ActivityUserinfoBinding>(), OnViewModelBaseListener {


    // -------- LOCAL VALUE --------
    lateinit var mViewModel : UserInfoViewModel
    // -----------------------------

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    override fun getViewType(): ActType = ActType.USER_INFO
    override fun getBaseTag(): String = getViewType().tag
    override fun getLayoutId(): Int = R.layout.activity_userinfo

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init(saveInstanceState: Bundle?) {
        mDisposable = CompositeDisposable()
        mViewModel = UserInfoViewModel().apply {
            this@apply.mDisposable = this@UserInfoActivity.mDisposable
            userLogin = intent?.extras?.getString("USER_LOGIN","") ?: ""
            if(CustomLog.flag)CustomLog.L(getBaseTag(),"userLogin",userLogin)
            if(TextUtils.isEmpty(userLogin)) finish()
            db = Db.getInstance(context = baseContext)!!
        }
        mBinding.viewModel = mViewModel

        setSupportActionBar(mBinding.toolbarheader)
        mBinding.toolbarLayout.title = mViewModel.userLogin

        getUserInfo()


        val sectionsPagerAdapter = UserInfoPageAdapter(this, supportFragmentManager).apply {
            this@apply.userLogin = this@UserInfoActivity.mViewModel.userLogin
            if(CustomLog.flag)CustomLog.L("UserInfoActivity","sectionsPagerAdapter",this@apply.userLogin)
        }
        mBinding.includeUserinfoContent.view_pager.adapter = sectionsPagerAdapter
        mBinding.includeUserinfoContent.tabs.setupWithViewPager(mBinding.includeUserinfoContent.view_pager)

    }

    override fun onDestroy() {
        super.onDestroy()
        Db.destroyInstance()
    }

    override fun onClick(type: Int, position: Int, value: Any?) {

    }

    ////////////////////////////////////////////////


    private fun getUserInfo(){
        mViewModel.getUserInfo()
        mViewModel.mUserInfo.observe(this, Observer {
            if(CustomLog.flag)CustomLog.L("UserInfoActivity","observe",it)
            mBinding.includeUserinfoHeader.imageviewUserAvatar.setImageURI(it.avatar_url)
            mBinding.includeUserinfoHeader.item = it
            mViewModel.getFavUserCheck(it.id)
            mBinding.includeUserinfoHeader.executePendingBindings()
        })
    }



}