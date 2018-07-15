package com.riiid.infiniteposts.riiidpostlist.ui.main.postlist.holder

import android.view.ViewGroup

interface MainBasePostListViewHolderFactory {
    fun createPostViewHolder(parent: ViewGroup): MainPostListViewHolder
}