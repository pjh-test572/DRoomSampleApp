package com.sample.pjh.gitusersearch.data.retrofit.server

import com.sample.pjh.gitusersearch.common.type.ServerType
import com.sample.pjh.gitusersearch.data.model.RepoModel
import com.sample.pjh.gitusersearch.data.model.SearchUserModel
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.manager.RetrofitManager
import com.sample.pjh.gitusersearch.data.retrofit.service.GitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class GitServer {

    companion object{

        fun getSearchUser(q : String, page : Int, listener : ServerResponseCallback<SearchUserModel>) : Disposable =
            RetrofitManager.createService(ServerType.GIT, GitService::class.java).getSearchUser(q,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listener.nextTask(it as SearchUserModel) },
                { e ->
                    if(e is HttpException) listener.failedTask(e, e.code())
                    else listener.failedTask(e, -1)
                }, {
                    listener.completeTask()
                }
            )


        fun getUserInfo(user : String, listener : ServerResponseCallback<UserModel>) : Disposable =
            RetrofitManager.createService(ServerType.GIT, GitService::class.java).getUserInfo(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listener.nextTask(it as UserModel) },
                { e ->
                    if(e is HttpException) listener.failedTask(e, e.code())
                    else listener.failedTask(e, -1)
                }, {
                    listener.completeTask()
                }
            )


        fun getUserRepos(user : String, listener : ServerResponseCallback<ArrayList<RepoModel>>) : Disposable =
            RetrofitManager.createService(ServerType.GIT, GitService::class.java).getUserRepos(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.nextTask(it as ArrayList<RepoModel>) },
                    { e ->
                        if(e is HttpException) listener.failedTask(e, e.code())
                        else listener.failedTask(e, -1)
                    }, {
                        listener.completeTask()
                    }
                )


    }

}