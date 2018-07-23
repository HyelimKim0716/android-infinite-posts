package com.riiid.infiniteposts.riiidpostlist.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseBindingActivity
import com.riiid.infiniteposts.riiidpostlist.common.LogMgr
import com.riiid.infiniteposts.riiidpostlist.databinding.ActivityMainBinding
import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailActivity
import com.riiid.infiniteposts.riiidpostlist.ui.main.favorite_post.FavoritePostFragment
import com.riiid.infiniteposts.riiidpostlist.ui.main.favorite_post.FavoritePostViewEvent
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostFragment
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostViewEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override val layoutId: Int = R.layout.activity_main

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var infinitePostFragment: InfinitePostFragment

    @Inject
    lateinit var favoritePostFragment: FavoritePostFragment

    private lateinit var fragmentPagerAdapter: MainTabFragmentPagerAdapter

    private var toolbarLayoutParams: AppBarLayout.LayoutParams ?= null


    private val disposables = CompositeDisposable()

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

//        isLoading = true
//        viewModel.loadPosts()

//        main_rvPosts.apply {
//            this.adapter = fragmentPagerAdapter
//
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//
//                    val linearLayoutManager = layoutManager as LinearLayoutManager
//
//                    val totalItemCount = linearLayoutManager.itemCount
//                    val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
//
//                    if (!isLoading && totalItemCount <= lastVisibleItemPosition.plus(1)) {
//                        viewModel.loadPosts()
//                        isLoading = true
//
//                    }
//                }
//            })
//        }


        setSupportActionBar(main_toolbar)

        supportActionBar?.setHomeButtonEnabled(true)
        toolbarLayoutParams = main_toolbar.layoutParams as? AppBarLayout.LayoutParams

        fragmentPagerAdapter = MainTabFragmentPagerAdapter(baseContext, supportFragmentManager).apply {
            clearFragment()
            addFragment(infinitePostFragment)
            addFragment(favoritePostFragment)
        }

        main_viewPager.adapter = fragmentPagerAdapter
        main_tabLayout.setupWithViewPager(main_viewPager)

        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                LogMgr.d("position: $position")
                fragmentPagerAdapter.getItem(position).refresh()
            }
        })
    }

    private fun bind() {
        disposables.add(viewModel.mainViewEventSender.observeOn(AndroidSchedulers.mainThread()).subscribe(::receiveMainEvent))
    }

    private fun unbind() {
        disposables.clear()
    }

    private fun receiveMainEvent(event: Pair<MainViewEvent, Any>) {
        LogMgr.d("receiveMainEvent ${event.first}")

        when (event.first) {

        }
    }







    private fun receiveInfinitePostsViewEvent(viewEvent: Pair<InfinitePostViewEvent, Any>) {
        when (viewEvent.first) {
            InfinitePostViewEvent.RECYCLER_VIEW_SCROLL_UP -> visibleTopWidgets(View.VISIBLE)
            InfinitePostViewEvent.RECYCLER_VIEW_SCROLL_DOWN -> visibleTopWidgets(View.GONE)
        }
    }

    private fun receiveFavoritePostsViewEvent(viewEvent: Pair<FavoritePostViewEvent, Any>) {
        when (viewEvent.first) {
            FavoritePostViewEvent.RECYCLER_VIEW_SCROLL_UP -> visibleTopWidgets(View.VISIBLE)
            FavoritePostViewEvent.RECYCLER_VIEW_SCROLL_DOWN -> visibleTopWidgets(View.GONE)
        }
    }

    private fun visibleTopWidgets(visibility: Int) {
        LogMgr.d("visibility = $visibility, appBarLayout: ${main_appBarLayout.visibility}, main_tabLayout: ${main_tabLayout.visibility}")

//        if (main_appBarLayout.visibility == visibility && main_tabLayout.visibility == visibility) return

        val appBarLayoutHeight = main_appBarLayout.height.toFloat()
        val tabLayoutHeight = main_tabLayout.height.toFloat()

        when (visibility) {
            View.VISIBLE -> {
                main_appBarLayout.let {
                    it.visibility = View.VISIBLE
                    it.animate().translationY(0F).setListener(null)
                }

                main_tabLayout.let {
                    it.visibility = View.VISIBLE
                    it.animate().translationY(0F)
                }
            }

            View.GONE -> {
                main_appBarLayout.animate().translationY(-appBarLayoutHeight).setListener(animatorListenerGoneAdapter)
                main_tabLayout.animate().translationY(- appBarLayoutHeight - tabLayoutHeight)
            }
        }
    }

    private val animatorListenerGoneAdapter = object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            main_appBarLayout.visibility = View.GONE
            main_tabLayout.visibility = View.GONE
        }
    }

}
