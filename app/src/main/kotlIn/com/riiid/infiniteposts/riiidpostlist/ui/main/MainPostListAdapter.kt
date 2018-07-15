package com.riiid.infiniteposts.riiidpostlist.ui.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.base.BaseViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.Post
import com.riiid.infiniteposts.riiidpostlist.ui.main.postlist.holder.MainPostListViewHolder
import com.riiid.infiniteposts.riiidpostlist.ui.main.postlist.holder.ProgressViewHolder

class MainPostListAdapter(private val viewModel: MainViewModel)
    : RecyclerView.Adapter<BaseViewHolder<Post>>() {

    val postList = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Post>
            = when (viewType) {
                MainPostListViewHolderType.DEFALUT -> MainPostListViewHolder(parent, viewModel)
                else -> ProgressViewHolder(parent)
            }


    override fun getItemCount(): Int = viewModel.postList.size.plus(1)

    override fun onBindViewHolder(holder: BaseViewHolder<Post>, position: Int) {
        when (holder) {
            is MainPostListViewHolder -> holder.onBindViewHolder(viewModel.postList[position], position)
            is ProgressViewHolder -> holder.onBindViewHolder(null, position)
        }
    }

    override fun getItemViewType(position: Int): Int
        = if (viewModel.postList.size > position) MainPostListViewHolderType.DEFALUT
        else MainPostListViewHolderType.ROADING
}