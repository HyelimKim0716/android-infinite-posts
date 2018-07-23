package com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.infinite_post_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.auto.factory.AutoFactory
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.PostItem

@AutoFactory(implementing = [(InfinitePostBaseRecyclerViewHolderFactory::class)])
class InfinitePostRecyclerViewProgressHolder(parent: ViewGroup)
    : BaseViewHolder<PostItem>(parent, LayoutInflater.from(parent.context).inflate(R.layout.item_main_post_list_progress_bar, parent, false)) {

    override fun onBindViewHolder(server: PostItem?, position: Int) {
        server ?: let {
            itemView.visibility = View.VISIBLE
        }
    }
}