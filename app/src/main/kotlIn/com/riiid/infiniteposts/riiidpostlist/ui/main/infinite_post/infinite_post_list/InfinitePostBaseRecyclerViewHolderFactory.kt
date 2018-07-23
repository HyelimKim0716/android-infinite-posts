package com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.infinite_post_list

import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.base.BaseViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.PostItem

interface InfinitePostBaseRecyclerViewHolderFactory {

    fun createInfinitePostRecyclerViewHolder(parent: ViewGroup): BaseViewHolder<PostItem>
}