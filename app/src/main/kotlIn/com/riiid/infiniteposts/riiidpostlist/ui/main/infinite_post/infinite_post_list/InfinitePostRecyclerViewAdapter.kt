package com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.infinite_post_list

import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.base.BaseRecyclerViewAdapter
import com.riiid.infiniteposts.riiidpostlist.base.BaseViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.PostItem
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostViewModel

class InfinitePostRecyclerViewAdapter(private val viewModel: InfinitePostViewModel,
                                      private val viewHolderFactories
                                      : Map<Int, @JvmSuppressWildcards InfinitePostBaseRecyclerViewHolderFactory>)
    : BaseRecyclerViewAdapter<BaseViewHolder<PostItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PostItem>
    = viewHolderFactories[viewType]?.createInfinitePostRecyclerViewHolder(parent)
            ?: InfinitePostRecyclerViewContentHolder(parent, viewModel)

    override fun getItemCount(): Int = viewModel.postList.size

    override fun onBindViewHolder(holder: BaseViewHolder<PostItem>, position: Int) {
        holder.onBindViewHolder(viewModel.postList[position], position)
    }

    override fun getItemViewType(position: Int): Int
    = if (viewModel.postList.size > position) InfinitePostListViewHolderType.DEFALUT
    else InfinitePostListViewHolderType.ROADING

}