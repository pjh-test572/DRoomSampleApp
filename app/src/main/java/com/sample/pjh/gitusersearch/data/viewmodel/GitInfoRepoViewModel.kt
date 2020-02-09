package com.sample.pjh.gitusersearch.data.viewmodel

import android.text.TextUtils
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.common.listener.OnGitUserViewListener
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.model.RepoModel
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.data.viewmodel.base.BaseObservableViewModel
import io.reactivex.disposables.CompositeDisposable


class GitInfoRepoViewModel : BaseObservableViewModel()  {

    lateinit var mDisposable : CompositeDisposable

    var mUserRepos: MutableLiveData<ArrayList<RepoModel>> = MutableLiveData()
    var userLogin = ""


    fun getUserRepoList(){
        mDisposable.add(
            GitServer.getUserRepos(user = userLogin,
                listener = ServerResponseCallback(nextTask = {
                    mUserRepos.value = it
                },completeTask = {

                },failedTask = { e, code ->

                })
            )
        )
    }


}
