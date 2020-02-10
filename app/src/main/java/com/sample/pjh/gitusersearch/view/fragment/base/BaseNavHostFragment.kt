package com.sample.pjh.gitusersearch.view.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.sample.pjh.gitusersearch.common.BaseApplication
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import io.reactivex.disposables.CompositeDisposable


abstract class BaseNavHostFragment<B : ViewDataBinding> : NavHostFragment() {
    ////////////////////////////////////////////////
    // PUBLIC
    ////////////////////////////////////////////////

    var isActivated = false
    var scrollState = 0

    // -------- LOCAL VALUE --------
    lateinit var mBinding: B
    lateinit var mContainer: ViewGroup
    lateinit var viewModelFactory : ViewModelProvider.Factory
    lateinit var mDisposable : CompositeDisposable
    lateinit var mLoadingIndicatorUtil : LoadingIndicatorUtil


    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    protected abstract val baseTag: String
    protected abstract val layoutId: Int
    // -----------------------------

    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            if(!::viewModelFactory.isInitialized) viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.requireContext().applicationContext as BaseApplication)

            init()
            return mBinding.root
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected abstract fun init()

    fun onPageScrollStateChanged(paramInt: Int) {
        this.scrollState = paramInt
    }

    fun getmBinding(): B {
        return mBinding
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mDisposable.isInitialized) mDisposable.dispose()
    }

    ////////////////////////////////////////////////
}