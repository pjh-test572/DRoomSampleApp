package com.sample.pjh.gitusersearch.data.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sample.pjh.gitusersearch.common.util.CustomLog
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.model.RepoModel
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.data.viewmodel.base.BaseObservableViewModel
import io.reactivex.disposables.CompositeDisposable

class UserInfoViewModel : BaseObservableViewModel()  {

    lateinit var mDisposable : CompositeDisposable
    lateinit var db : Db

    var userLogin = ""
    var mUserInfo: MutableLiveData<UserModel> = MutableLiveData()
    var mUserRepos: MutableLiveData<ArrayList<RepoModel>> = MutableLiveData()



    fun getUserInfo(){
        mDisposable.add(
            GitServer.getUserInfo(user = userLogin,
                listener = ServerResponseCallback(nextTask = {
                    mUserInfo.value = it
                },completeTask = {

                },failedTask = { e, code ->

                })
            )
        )
    }

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