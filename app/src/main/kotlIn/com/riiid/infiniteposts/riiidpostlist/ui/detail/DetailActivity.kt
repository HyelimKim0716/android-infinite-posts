package com.riiid.infiniteposts.riiidpostlist.ui.detail

import android.os.Bundle
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseBindingActivity
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import com.riiid.infiniteposts.riiidpostlist.databinding.ActivityDetailBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseBindingActivity<ActivityDetailBinding>() {

    override val layoutId: Int = R.layout.activity_detail

    @Inject
    lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var adapter: DetailCommentListAdapter

    private val disposables = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unbind()
    }

    private fun bind() {
        disposables.add(viewModel.detailViewEventSender.observeOn(AndroidSchedulers.mainThread()).subscribe(::receiveEvent))
    }

    private fun unbind() {
        disposables.clear()
    }

    override fun setSupportActionBar(toolbar: android.support.v7.widget.Toolbar?) {
        toolbar?.title = "Details"
        super.setSupportActionBar(toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.detailViewModel = viewModel
        detail_rvComments.adapter = adapter

        val post = intent.getParcelableExtra<ServerPost>("serverPost")
        viewModel.serverPost = post
        viewModel.loadComments()
    }

    private fun receiveEvent(event: Pair<DetailViewEvent, Any>) {
        when (event.first) {
            DetailViewEvent.REFRESH_COMMENT_LIST -> refreshCommentList()
        }

    }

    private fun refreshCommentList() {
        adapter.commentList.addAll(viewModel.commentList)
        adapter.notifyDataSetChanged()
    }
}
