package com.riiid.infiniteposts.riiidpostlist.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseBindingActivity
import com.riiid.infiniteposts.riiidpostlist.databinding.ActivityMainBinding
import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private val tag = "MainActivity"
    override val layoutId: Int = R.layout.activity_main

    private val disposables = CompositeDisposable()

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var postListAdapter: MainPostListAdapter

    var isLoading = false

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unbind()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        isLoading = true
        viewModel.loadPosts()

        main_rvPosts.apply {
            this.adapter = postListAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val linearLayoutManager = layoutManager as LinearLayoutManager

                    val totalItemCount = linearLayoutManager.itemCount
                    val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()

                    if (!isLoading && totalItemCount <= lastVisibleItemPosition.plus(1)) {
                        viewModel.loadPosts()
                        isLoading = true

                    }
                }
            })
        }
    }

    private fun bind() {
        disposables.add(viewModel.mainViewEventSender.observeOn(AndroidSchedulers.mainThread()).subscribe(::receiveMainEvent))
    }

    private fun unbind() {
        disposables.clear()
    }

    private fun receiveMainEvent(event: Pair<MainViewEvent, Any>) {
        Log.d(tag, "receiveMainEvent ${event.first}")

        when (event.first) {
            MainViewEvent.REFRESH_POST_LIST -> refreshPostList()
            MainViewEvent.ITEM_CLICKED -> (event.second as? Int)?.let { startDetailActivity(it) }
            MainViewEvent.ITEM_DELETED -> (event.second as? Int)?.let { deleteItem(it) }
        }
    }

    private fun refreshPostList() {
        isLoading = false
        postListAdapter.postList.addAll(viewModel.postList)
        postListAdapter.notifyDataSetChanged()
    }

    private fun startDetailActivity(position: Int) {
        viewModel.postList[position]?.let { post ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    .apply {
                        putExtra("post", post)
                    }

            startActivity(intent)
        }
    }

    private fun deleteItem(position: Int){
        viewModel.postList.removeAt(position)
        postListAdapter.postList.removeAt(position)
        postListAdapter.notifyDataSetChanged()
    }

}
