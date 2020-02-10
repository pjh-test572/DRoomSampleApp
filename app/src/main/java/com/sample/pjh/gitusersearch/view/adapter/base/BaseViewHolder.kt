package com.sample.pjh.gitusersearch.view.adapter.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * 기본 view holder
 */
abstract class BaseViewHolder<B : ViewDataBinding, T> (itemView: View) : RecyclerView.ViewHolder(itemView) {

    // -------- LOCAL VALUE --------
    var binding: B = DataBindingUtil.bind(itemView)!!

    abstract fun bind(item: T, position: Int)


    ////////////////////////////////////////////////
}