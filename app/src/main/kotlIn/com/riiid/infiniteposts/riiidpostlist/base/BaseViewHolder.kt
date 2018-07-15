package com.riiid.infiniteposts.riiidpostlist.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class BaseViewHolder<in ITEM>(parent: ViewGroup, itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context = parent.context

    abstract fun onBindViewHolder(item: ITEM?, position: Int)
}
