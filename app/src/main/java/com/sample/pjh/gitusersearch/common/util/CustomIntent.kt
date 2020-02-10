package com.sample.pjh.gitusersearch.common.util

import android.app.Activity
import android.content.Intent
import com.sample.pjh.gitusersearch.common.type.ActType
import java.io.Serializable

object CustomIntent {


    fun startIntent(act : Activity, actType : ActType){
        startIntent(act, actType,null, null)
    }

    fun startIntent(act : Activity, actType : ActType, key: String?, value : String?){
        val intent = Intent(act, actType.actClass)
        intent.putExtra(key, value)
        act.startActivityForResult(intent,actType.requestCode)
    }

}