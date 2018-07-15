package com.riiid.infiniteposts.riiidpostlist.ui.main.postlist.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseDataBindingViewHolder
import com.riiid.infiniteposts.riiidpostlist.data.model.Post
import com.riiid.infiniteposts.riiidpostlist.databinding.ItemMainPostListBinding
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewEvent
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewModel
import kotlinx.android.synthetic.main.item_main_post_list.view.*

class MainPostListViewHolder(val parent: ViewGroup, val viewModel: MainViewModel)
    : BaseDataBindingViewHolder<ItemMainPostListBinding, Post>(parent,
        LayoutInflater.from(parent.context).inflate(R.layout.item_main_post_list, parent, false)) {

    override fun onBindViewHolder(item: Post?, position: Int) {
        itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)
        binding?.post = item


        itemView.postItem_llOneItem.setOnClickListener {
            viewModel.mainViewEventSender.onNext(MainViewEvent.ITEM_CLICKED to adapterPosition)
        }

        itemView.postItem_btnDelete.setOnClickListener {
            viewModel.mainViewEventSender.onNext(MainViewEvent.ITEM_DELETED to adapterPosition)
        }
    }
}