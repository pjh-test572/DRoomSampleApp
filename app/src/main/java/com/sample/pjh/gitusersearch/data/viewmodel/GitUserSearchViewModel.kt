package com.sample.pjh.gitusersearch.data.viewmodel

import android.text.TextUtils
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.common.listener.OnGitUserViewListener
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.db.entity.NewlyWordEntity
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.data.viewmodel.base.BaseObservableViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class GitUserSearchViewModel : BaseObservableViewModel()  {

    lateinit var mDisposable : CompositeDisposable
    lateinit var db : Db
    lateinit var listener : OnGitUserViewListener<ArrayList<UserModel>>

    var searchTxt = ""

    var isLoading = false
    var page = 0
    var totalPage = 0
    var totalItemCount = 0

    var gitUserSearchClear = ObservableBoolean(false) // ObservableInt(View.GONE)
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.gitUserSearchClear)
        }

    var emptyViewVisible = ObservableBoolean(true) // ObservableInt(View.GONE)
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.emptyViewVisible)
        }

    fun setInitPage(){
        page = 0
        totalPage = 0
        totalItemCount = 0
    }

    fun onClickVIew(view : View){
        if(::listener.isInitialized)listener.onClick(view.id,0,view)
    }


    fun getSearchUser(){
        if(!TextUtils.isEmpty(searchTxt)){
            page += 1
            mDisposable.add(GitServer.getSearchUser(searchTxt ?: "", page,
                ServerResponseCallback(nextTask = {
                    if(totalItemCount == 0) {
                        totalItemCount = it.total_count
                        totalPage = it.total_count / Info.PAGE_PER_ITEM + (if(it.total_count % Info.PAGE_PER_ITEM > 0) 1 else 0)
                    }
                    if(it.total_count > 0){
                        addSearchWord(searchTxt, it.total_count)
                    }
                    if(::listener.isInitialized)listener.onNext(it.list)
                },failedTask = {e, code ->
                    isLoading = false
                },completeTask = {
                    isLoading = false
                }))
            )
        }
    }


    private fun addSearchWord(word : String, totalCount : Int){
        mDisposable.add(Observable.just(NewlyWordEntity(0,word,totalCount, Calendar.getInstance().timeInMillis)).subscribeOn(Schedulers.io()).subscribe {
            db.newlyWordDao().delete(it.searchWord)
            db.newlyWordDao().insert(it)
        })
    }

}
