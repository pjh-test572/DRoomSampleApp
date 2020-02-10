package com.sample.pjh.gitusersearch.common.util

import androidx.appcompat.app.AppCompatActivity
import com.sample.pjh.gitusersearch.common.dialog.CustomMessageDialog
import com.sample.pjh.gitusersearch.common.listener.OnBaseDialogListener

object CommonDialogUtil {

    /***
     *
     * @param activity current Activity notNull
     * @param mas String type notNull
     * @param isCancelBtn Boolean data notNull
     * @param listener OnBaseDialogListener nullable
     */
    fun showDialog(activity: AppCompatActivity, mas: String, isCancelBtn: Boolean, listener: OnBaseDialogListener?) {
        CustomMessageDialog(message = mas, cancelButtonVisible = isCancelBtn,
            confirmTask = {
                listener?.onClickOk()
            }
        ).show(manager = activity.supportFragmentManager, tag = activity::class.java.simpleName)
    }

    /***
     *
     * @param activity current Activity notNull
     * @param mas String type notNull
     * @param isCancelBtn Boolean data notNull
     * @param listener OnBaseDialogListener nullable
     */
    fun showDialog(activity: AppCompatActivity, mas: String, cancelListener: OnBaseDialogListener?, okListener: OnBaseDialogListener?) {
        CustomMessageDialog(message = mas,
            cancelTask = {
                cancelListener?.onClickOk()
            },
            confirmTask = {
                okListener?.onClickOk()
            }
        ).show(manager = activity.supportFragmentManager, tag = activity::class.java.simpleName)
    }

    /***
     *
     * @param activity current Activity notNull
     * @param mas String type notNull
     * @param isCancelBtn Boolean data notNull
     * @param listener OnBaseDialogListener nullable
     */
    fun showDialog(activity: AppCompatActivity, mas: String, cancelListener: OnBaseDialogListener?, okListener: OnBaseDialogListener?, confirmBtnName: String) {
        CustomMessageDialog(message = mas,
            cancelTask = {
                cancelListener?.onClickOk()
            },
            confirmTask = {
                okListener?.onClickOk()
            },
            confirmBtnName = confirmBtnName
        ).show(manager = activity.supportFragmentManager, tag = activity::class.java.simpleName)
    }


    /***
     *
     * @param activity current Activity notNull
     * @param mas String type notNull
     * @param isCancelBtn Boolean data notNull
     * @param isFinish OnBaseDialogListener nullable
     */
    fun showDialog(activity: AppCompatActivity, mas: String, isCancelBtn: Boolean, isFinish: Boolean) {
        CustomMessageDialog(message = mas, cancelButtonVisible = isCancelBtn,
            confirmTask = {
                if (isFinish) activity.finish()
            }
        ).show(manager = activity.supportFragmentManager, tag = activity::class.java.simpleName)
    }

}