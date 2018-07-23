package com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseDataBindingFragment
import com.riiid.infiniteposts.riiidpostlist.databinding.FragmentInfinitePostsBinding
import com.riiid.infiniteposts.riiidpostlist.ui.adapter.StickyItemDecoration
import com.riiid.infiniteposts.riiidpostlist.ui.adapter.StickyItemDecorationCallback
import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailActivity
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainActivity
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewEvent
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewModel
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.infinite_post_list.InfinitePostRecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_infinite_posts.*
import java.text.FieldPosition
import javax.inject.Inject

class InfinitePostFragment: BaseDataBindingFragment<FragmentInfinitePostsBinding>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var infinitePostViewModel: InfinitePostViewModel

    @Inject
    lateinit var postAdapter: InfinitePostRecyclerViewAdapter

    @Inject
    lateinit var itemDecoration: StickyItemDecoration

    override val layoutId: Int = R.layout.fragment_infinite_posts

    val infinitePostViewEventSender
            = PublishSubject.create<Pair<InfinitePostViewEvent, Any>>().apply { subscribeOn(AndroidSchedulers.mainThread()) }

    private val disposables = CompositeDisposable()

    private lateinit var recyclerViewManager: LinearLayoutManager

    private var prevFirstVisiblePosition = 0

    private var isLoading = false

    override fun refresh() {

    }

    override fun onResume() {
        bind()
        super.onResume()
    }

    override fun onPause() {
        unbind()
        super.onPause()
    }

    private fun bind() {
        disposables.add(mainViewModel.mainViewEventSender.observeOn(AndroidSchedulers.mainThread()).subscribe(::receiveMainViewEvent))
        disposables.add(infinitePostViewModel.infinitePostViewEventSender.observeOn(AndroidSchedulers.mainThread()).subscribe(::receiveInfinitePostsViewEvent))
    }

    private fun unbind() {
        disposables.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = infinitePostViewModel
        itemDecoration.stickyItemDecorationCallback = decorationCallback
        infinitePosts_rvPosts.let {
            it.adapter = postAdapter
            it.addOnScrollListener(scrollListener)
            it.addItemDecoration(itemDecoration)
            recyclerViewManager = it.layoutManager as LinearLayoutManager
            prevFirstVisiblePosition = recyclerViewManager.findFirstCompletelyVisibleItemPosition()
        }

        isLoading = true
        infinitePostViewModel.loadPosts()
    }

    private fun receiveMainViewEvent(event: Pair<MainViewEvent, Any>) {
        when (event.first) {

        }
    }

    private fun receiveInfinitePostsViewEvent(event: Pair<InfinitePostViewEvent, Any>) {
       when (event.first) {
           // from viewModel
           InfinitePostViewEvent.REFRESH_POST_LIST -> refreshPostList()
           InfinitePostViewEvent.CLICK_VIEW_HOLDER -> (event.second as? Int)?.let { startDetailActivity(it) }
           InfinitePostViewEvent.CLICK_DELETE_ICON -> (event.second as? Int)?.let { deleteItem(it) }
           InfinitePostViewEvent.CHECK_FAVORITE_USER -> (event.second as? Int)?.let { deleteItem(it) }
       }
    }

    private fun refreshPostList() {
        isLoading = false
        postAdapter.notifyDataSetChanged()
    }

    private fun startDetailActivity(position: Int) {
        infinitePostViewModel.postList[position]?.let { post ->
            val intent = Intent(activity, DetailActivity::class.java)
                                    .apply { putExtra("post", post) }

            activity?.startActivity(intent)
        }
    }

    private fun deleteItem(position: Int) {
        infinitePostViewModel.postList.removeAt(position)
        postAdapter.notifyItemRemoved(position)
    }





    private fun sendInfinitePostViewEvent(viewEvent: InfinitePostViewEvent, data: Any) {
        infinitePostViewEventSender.onNext(viewEvent to data)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            // Hide Top Widgets
            val currentFirstVisiblePosition = recyclerViewManager.findFirstVisibleItemPosition()

            if (currentFirstVisiblePosition == prevFirstVisiblePosition) return

            if (currentFirstVisiblePosition - prevFirstVisiblePosition < -1) {
                sendInfinitePostViewEvent(InfinitePostViewEvent.RECYCLER_VIEW_SCROLL_DOWN, 0)  // Show
                prevFirstVisiblePosition = currentFirstVisiblePosition
            } else if (currentFirstVisiblePosition - prevFirstVisiblePosition > 1) {
                sendInfinitePostViewEvent(InfinitePostViewEvent.RECYCLER_VIEW_SCROLL_UP, 0)    // hide
                prevFirstVisiblePosition = currentFirstVisiblePosition
            }


            // Load More Posts
            val totalItemCount = recyclerViewManager.itemCount
            val lastVisibleItemPosition = recyclerViewManager.findLastVisibleItemPosition()

            if (!isLoading && totalItemCount <= lastVisibleItemPosition.plus(1)) {
                infinitePostViewModel.loadPosts()
                isLoading = true
            }

        }
    }

    private val decorationCallback = object : StickyItemDecorationCallback {
        override fun isSection(position: Int): Boolean
        = position == 0
        || infinitePostViewModel
                .postList[position]
                .title
                .toCharArray()[0]
                .toLowerCase() != infinitePostViewModel
                                    .postList[position.minus(1)]
                                    .title
                                    .toCharArray()[0]
                                    .toLowerCase()

        override fun getSectionHeader(position: Int): CharSequence
        = infinitePostViewModel.postList[position].title.subSequence(0, 1)
    }
}