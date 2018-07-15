package com.riiid.infiniteposts.riiidpostlist.ui.main.postlist.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.Post

class ProgressViewHolder(parent: ViewGroup)
    : BaseViewHolder<Post>(parent, LayoutInflater.from(parent.context).inflate(R.layout.item_main_post_list_progress_bar, parent, false)) {

    override fun onBindViewHolder(item: Post?, position: Int) {
        item ?: let {
            itemView.visibility = View.VISIBLE
        }
    }
}