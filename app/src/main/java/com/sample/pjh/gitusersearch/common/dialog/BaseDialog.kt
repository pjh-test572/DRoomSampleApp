package com.sample.pjh.gitusersearch.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.sample.pjh.gitusersearch.R

/**
 * 기본 다이얼로그
 */
abstract class BaseDialog<B : ViewDataBinding> : DialogFragment() {

    // -------- LOCAL VALUE --------

    lateinit var mBinding: B

    // -----------------------------

    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        init()
        return mBinding.root
    }

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    protected abstract fun getLayoutId():Int

    protected abstract fun init()

    ////////////////////////////////////////////////
}