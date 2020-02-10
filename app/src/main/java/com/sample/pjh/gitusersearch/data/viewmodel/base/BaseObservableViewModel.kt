package com.sample.pjh.gitusersearch.data.viewmodel.base


import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

/**
 * viewModel에 연결되어 xml에 Observable 된 변수의 notifyCallbacks 을 위한 기본 뷰모델
 */
open class BaseObservableViewModel : ViewModel(), Observable {
    private var registry: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        if (registry == null) {
            registry = PropertyChangeRegistry()
        }

        registry!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        if (registry == null) {
            registry = PropertyChangeRegistry()
        }

        registry!!.remove(callback)
    }

    fun notifyChange() {
        synchronized(this) {
            if (registry == null) return
        }
    }

    fun notifyPropertyChanged(id: Int) {
        synchronized(this) {
            if (registry == null) return
        }
        registry!!.notifyCallbacks(this, id, null)
    }

    fun destroyModel() {
        registry?.clear()
    }

}

