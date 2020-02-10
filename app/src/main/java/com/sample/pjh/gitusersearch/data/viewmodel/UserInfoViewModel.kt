package com.sample.pjh.gitusersearch.data.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.db.entity.GitUserEntity
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.data.viewmodel.base.BaseObservableViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserInfoViewModel : BaseObservableViewModel()  {

    lateinit var mDisposable : CompositeDisposable
    lateinit var db : Db

    var userLogin = ""
    var mUserInfo: MutableLiveData<UserModel> = MutableLiveData()


    var userIsFavChecked = ObservableBoolean(false) // ObservableInt(View.GONE)
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.userIsFavChecked)
        }

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


    fun getFavUserCheck(id : Int){
        mDisposable.add(
            Observable.fromCallable<List<GitUserEntity>> {
                db.gitUserDao().get(id)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    userIsFavChecked.set(result.isNotEmpty())
                }
        )
    }


}