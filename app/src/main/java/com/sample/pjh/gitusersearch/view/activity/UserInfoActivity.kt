package com.sample.pjh.gitusersearch.view.activity

import android.text.TextUtils
import androidx.lifecycle.Observer
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.common.util.CustomLog
import com.sample.pjh.gitusersearch.data.viewmodel.UserInfoViewModel
import com.sample.pjh.gitusersearch.databinding.ActivityUserinfoBinding
import com.sample.pjh.gitusersearch.view.activity.base.BindActivity
import com.sample.pjh.gitusersearch.view.adapter.RepoListAdapter
import io.reactivex.disposables.CompositeDisposable

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

    override fun init() {
        mDisposable = CompositeDisposable()
        mViewModel = UserInfoViewModel().apply {
            this@apply.mDisposable = this@UserInfoActivity.mDisposable
            userLogin = intent?.extras?.getString("USER_LOGIN","") ?: ""
            if(CustomLog.flag)CustomLog.L(getBaseTag(),"userLogin",userLogin)
            if(TextUtils.isEmpty(userLogin)) finish()
        }
        mBinding.viewModel = mViewModel

        setSupportActionBar(mBinding.toolbar)
        mBinding.toolbarLayout.title = mViewModel.userLogin

        getUserInfo()
        getUserRepoList()

        mBinding.executePendingBindings()
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
            mBinding.includeUserinfoHeader.executePendingBindings()
            /*mBinding.includeUserinfoHeader.textviewUserName.text = it.name
            mBinding.includeUserinfoHeader.textviewUserPlace.text = it.company
            mBinding.includeUserinfoHeader.textviewUserMail.text = it.email
            mBinding.includeUserinfoHeader.textviewUserUrl.text = it.url*/
        })
    }


    private fun getUserRepoList(){
        mViewModel.getUserRepoList()
        mViewModel.mUserRepos.observe(this, Observer {
            mBinding.recyclerview.adapter = RepoListAdapter().apply {
                mContext = this@UserInfoActivity
                listener = this@UserInfoActivity
                setList(it)
            }
        })
    }


}