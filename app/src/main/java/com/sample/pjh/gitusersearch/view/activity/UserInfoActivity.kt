package com.sample.pjh.gitusersearch.view.activity

import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.databinding.ActivityUserinfoBinding
import com.sample.pjh.gitusersearch.view.activity.base.BindActivity

class UserInfoActivity : BindActivity<ActivityUserinfoBinding>() {


    // -------- LOCAL VALUE --------
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

    }



    ////////////////////////////////////////////////
}