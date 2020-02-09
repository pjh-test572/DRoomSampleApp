package com.sample.pjh.gitusersearch.common.type

import com.sample.pjh.gitusersearch.view.activity.MainActivity
import com.sample.pjh.gitusersearch.view.activity.UserInfoActivity
import com.sample.pjh.gitusersearch.view.activity.base.BaseActivity


const val BaseRequestCode : Int = 1000

/**
 * Activity Type enum
 *
 * @author jungho park
 */
enum class ActType (val actClass : Class<out BaseActivity>, val tag: String, val isMainAct : Boolean, val requestCode : Int){
    MAIN(MainActivity::class.java, MainActivity::class.java.simpleName, true, BaseRequestCode),
    USER_INFO(UserInfoActivity::class.java, UserInfoActivity::class.java.simpleName, true, 10101)
}
