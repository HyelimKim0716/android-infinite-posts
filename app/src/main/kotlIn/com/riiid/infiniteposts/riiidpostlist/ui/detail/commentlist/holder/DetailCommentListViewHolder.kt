package com.riiid.infiniteposts.riiidpostlist.ui.detail.commentlist.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseDataBindingViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.Comment
import com.riiid.infiniteposts.riiidpostlist.databinding.ItemDetailCommentListBinding

class DetailCommentListViewHolder(parent:ViewGroup)
    : BaseDataBindingViewHolder<ItemDetailCommentListBinding, Comment>(parent,
        LayoutInflater.from(parent.context).inflate(R.layout.item_detail_comment_list, parent, false)) {

    override fun onBindViewHolder(item: Comment?, position: Int) {
        binding?.comment = item
    }

}