package com.sample.pjh.gitusersearch.view.activity.base

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider


/**
 * BindActivity
 * bind layout 을 지원하는 activity
 *
 * @author jungho park
 *
 */
abstract class BindActivity<B : ViewDataBinding> : BaseActivity() {


    // -------- LOCAL VALUE --------

    lateinit var mBinding: B
    lateinit var viewModelFactory : ViewModelProvider.Factory

    // -----------------------------


    ////////////////////////////////////////////////
    // ACTIVITY LIFE CYCLE OVERRIDE
    ////////////////////////////////////////////////

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        try {
            mBinding = DataBindingUtil.setContentView(this, getLayoutId())
            if(!::viewModelFactory.isInitialized) viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            init(saveInstanceState)
        } catch (e: Exception) {
            showErrorDialog(e, true)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    ////////////////////////////////////////////////



    ////////////////////////////////////////////////


    //////////////////////////////////////////////

}
