package com.sample.pjh.gitusersearch.view.activity.base

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.common.listener.OnBaseDialogListener
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.common.util.CommonDialogUtil
import io.reactivex.disposables.CompositeDisposable

/**
 * BaseActivity
 * 공통 기본 activity
 *
 * @author jungho park
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    // -------- CONST VALUE --------

    val PERMISSIONS_REQUEST_CODE = 200

    // Device android version 23 이상에서 앱 사용에 필요한 필수 권환
    //val REQUIRED_PERMISSIONS = Manifest.permission.WRITE_EXTERNAL_STORAGE

    val REQUIRED_PERMISSIONS_29 = Manifest.permission.READ_EXTERNAL_STORAGE


    // -----------------------------


    // -------- LOCAL VALUE --------

    lateinit var mHandler: Handler
    lateinit var mLoadingIndicatorUtil : LoadingIndicatorUtil
    lateinit var mDisposable : CompositeDisposable

    var mTimeOutDialog: Runnable = Runnable {
        CommonDialogUtil.showDialog(
            this@BaseActivity,
            resources.getString(R.string.common_message_timeout),
            false, object : OnBaseDialogListener {
                override fun onClickOk() {
                    finish()
                }
            })
    }
    // -----------------------------


    ////////////////////////////////////////////////
    // ACTIVITY LIFE CYCLE OVERRIDE
    ////////////////////////////////////////////////

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        Fresco.initialize(this)
        mHandler = Handler(this.mainLooper)
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
        try{
            if (::mLoadingIndicatorUtil.isInitialized)  mLoadingIndicatorUtil.dismiss()
            if (::mDisposable.isInitialized) mDisposable.dispose()
        }catch (e : Exception){
        }
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

    // 공통 토스트 메시지
    fun showToast(msg: Any?) {
        if(msg != null){
            var message = when(msg){
                is String -> msg
                is Int -> getString(msg)
                else -> ""
            }
            if(!TextUtils.isEmpty(message)) Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    }

    // 공통 에러 메시지 다이얼로그
    fun showErrorDialog(msg : Any?, isFinish : Boolean = false){
        try {
            var message : String = when(msg){
                is String -> msg
                is Exception -> msg.message ?: if(isFinish) resources.getString(R.string.common_message_error_finish) else resources.getString(R.string.common_message_error)
                else -> if(isFinish) resources.getString(R.string.common_message_error_finish) else resources.getString(R.string.common_message_error)
            }
            CommonDialogUtil.showDialog(
                this@BaseActivity,
                message,
                false, object : OnBaseDialogListener {
                    override  fun onClickOk() {
                        if(isFinish) finish()
                    }
                }
            )
        }catch (e : Exception){
            finish()
        }
    }


    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    protected abstract fun getBaseTag(): String

    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewType(): ActType

    protected abstract fun init()


    ////////////////////////////////////////////////


    ////////////////////////////////////////////////



    ////////////////////////////////////////////////
}
