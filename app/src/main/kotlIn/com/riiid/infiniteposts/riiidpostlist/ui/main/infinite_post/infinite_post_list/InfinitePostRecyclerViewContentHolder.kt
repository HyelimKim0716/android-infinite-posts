package com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.infinite_post_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseDataBindingViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.PostItem
import com.riiid.infiniteposts.riiidpostlist.databinding.ItemInfinitePostListBinding
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostViewEvent
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostViewModel
import kotlinx.android.synthetic.main.item_infinite_post_list.view.*

@AutoFactory(implementing = [(InfinitePostBaseRecyclerViewHolderFactory::class)])
class InfinitePostRecyclerViewContentHolder(parent: ViewGroup, @Provided val viewModel: InfinitePostViewModel)
    : BaseDataBindingViewHolder<ItemInfinitePostListBinding, PostItem>(parent,
        LayoutInflater.from(parent.context).inflate(R.layout.item_infinite_post_list, parent, false)) {

    override fun onBindViewHolder(postItem: PostItem?, position: Int) {
        binding?.postItem = postItem

        Glide.with(context)
                .load(postItem?.thumbnailUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.infinitePostItem_ivUser)

        itemView.setOnClickListener { viewModel.sendInfinitePostViewEvent(InfinitePostViewEvent.CLICK_VIEW_HOLDER, adapterPosition) }
        itemView.infinitePostItem_btnDelete.setOnClickListener { viewModel.sendInfinitePostViewEvent(InfinitePostViewEvent.CLICK_DELETE_ICON, adapterPosition) }
        itemView.infinitePostItem_cbFavorite.setOnClickListener { viewModel.sendInfinitePostViewEvent(InfinitePostViewEvent.CHECK_FAVORITE_USER, adapterPosition) }
    }
}
