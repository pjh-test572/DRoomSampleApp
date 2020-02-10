package com.sample.pjh.gitusersearch.common.listener

interface OnGitUserViewListener<T> : OnViewModelBaseListener {

    fun onNext(t : T)

}