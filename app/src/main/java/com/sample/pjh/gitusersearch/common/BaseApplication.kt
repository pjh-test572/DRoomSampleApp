package com.sample.pjh.gitusersearch.common

import androidx.multidex.MultiDexApplication

class BaseApplication : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }


    companion object {
        lateinit var instance: BaseApplication
            private set
    }

}