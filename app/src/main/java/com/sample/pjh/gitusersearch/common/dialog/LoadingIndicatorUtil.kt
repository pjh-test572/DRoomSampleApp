package com.sample.pjh.gitusersearch.common.dialog


import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.databinding.LayoutLoadingindicatorBinding


/**
 * Circle Loading Indicator
 * 로딩 Progress Bar
 */
class LoadingIndicatorUtil(val mContext: Context) : Dialog(mContext) {
    init {
        val binding = DataBindingUtil.inflate<LayoutLoadingindicatorBinding>(LayoutInflater.from(mContext), R.layout.layout_loadingindicator, null, false)

        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        setContentView(binding.root)
    }

    override fun hide() {
        if (this.isShowing)
            super.hide()
    }

    override fun show() {
        if (!this.isShowing)
            super.show()
    }

}