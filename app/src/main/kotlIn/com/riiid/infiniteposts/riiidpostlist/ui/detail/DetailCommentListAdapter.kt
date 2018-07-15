package com.riiid.infiniteposts.riiidpostlist.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.base.BaseViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.Comment
import com.riiid.infiniteposts.riiidpostlist.ui.detail.commentlist.holder.DetailCommentListViewHolder

class DetailCommentListAdapter: RecyclerView.Adapter<BaseViewHolder<Comment>>() {
    val commentList = ArrayList<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Comment>
    = DetailCommentListViewHolder(parent)

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: BaseViewHolder<Comment>, position: Int) {
        holder.onBindViewHolder(commentList[position], position)
    }
}