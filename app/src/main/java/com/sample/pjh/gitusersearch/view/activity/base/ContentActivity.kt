package com.sample.pjh.gitusersearch.view.activity.base

import android.content.Intent
import android.os.Bundle

/**
 * ContentActivity
 * layout 을 지원하는 activity
 *
 * @author jungho park
 *
 */
abstract class ContentActivity : BaseActivity() {


    // -------- LOCAL VALUE --------


    // -----------------------------


    ////////////////////////////////////////////////
    // ACTIVITY LIFE CYCLE OVERRIDE
    ////////////////////////////////////////////////

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        try {
            setContentView(getLayoutId())

            init()
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