package com.sample.pjh.gitusersearch.common.type

import com.sample.pjh.gitusersearch.BuildConfig


// Build
enum class BuildType {
    DEV,
    QA,
    RELEASE
}

enum class ServerType(val type : Int, val label : String, val url : String) {
    GIT(0, "Git", ServerUtil.getUrl(BuildConfig.BuildType))

}



object ServerUtil {

    fun getUrl(type : BuildType) : String = when(type){
        BuildType.DEV->"https://api.github.com"
        BuildType.RELEASE->"https://api.github.com"
        else->"https://api.github.com"
    }

}


