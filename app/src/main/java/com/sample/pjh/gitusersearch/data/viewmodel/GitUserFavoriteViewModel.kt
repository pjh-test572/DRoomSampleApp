package com.sample.pjh.gitusersearch.data.viewmodel

import com.sample.pjh.gitusersearch.common.listener.OnGitUserViewListener
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.viewmodel.base.BaseObservableViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GitUserFavoriteViewModel : BaseObservableViewModel()  {
    private val repository = GitUserFavoriteRepository()

    lateinit var mDisposable : CompositeDisposable
    lateinit var db : Db
    lateinit var listener : OnGitUserViewListener<ArrayList<UserModel>>


    fun getFavUser(){
        mDisposable.add(repository.getFavUser(db,
            ServerResponseCallback(nextTask = {
                if(::listener.isInitialized)listener.onNext(it)
            },failedTask = {e, code ->
            },completeTask = { }))
        )
    }

}




class GitUserFavoriteRepository{


    fun getFavUser(db : Db, listener : ServerResponseCallback<ArrayList<UserModel>>) : Disposable = Observable.fromCallable<ArrayList<UserModel>> {
        var allList = db.gitUserDao().getAll()
        var list = arrayListOf<UserModel>()
        if(!allList.isNullOrEmpty()){
            for(value in allList) list.add(value.getUser())
        }
        list
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            listener.nextTask(it)
        },
            { e -> listener.failedTask(e, -1)
            }, { listener.completeTask() })

}