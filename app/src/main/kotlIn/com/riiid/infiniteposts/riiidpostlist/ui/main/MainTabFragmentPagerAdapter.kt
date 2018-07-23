package com.riiid.infiniteposts.riiidpostlist.ui.main

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.riiid.infiniteposts.riiidpostlist.R
import com.riiid.infiniteposts.riiidpostlist.base.BaseFragment

class MainTabFragmentPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val FRAGMENT_LOAD_INFINITE_POSTS_POSITION = 0
    private val FRAGMENT_FAVORITE_POSTS_POSITION = 1

    private val fragmentList = ArrayList<BaseFragment>()

    fun clearFragment() {
        fragmentList.clear()
    }

    fun addFragment(fragment: BaseFragment) {
        fragmentList.add(fragment)
    }

    override fun getItem(position: Int): BaseFragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        FRAGMENT_LOAD_INFINITE_POSTS_POSITION -> context.resources.getString(R.string.infinite_post_list_page_title)
        FRAGMENT_FAVORITE_POSTS_POSITION -> context.resources.getString(R.string.favorite_post_list_page_title)
        else -> null
    }
}

//class MainPostListAdapter(private val viewModel: MainViewModel)
//    : RecyclerView.Adapter<BaseViewHolder<ServerPost>>() {
//
//    val postList = ArrayList<ServerPost>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ServerPost>
//            = when (viewType) {
//                InfinitePostListViewHolderType.DEFALUT -> MainPostListViewHolder(parent, viewModel)
//                else -> InfinitePostRecyclerViewProgressHolder(parent)
//            }
//
//
//    override fun getItemCount(): Int = viewModel.postList.size.plus(1)
//
//    override fun onBindViewHolder(holder: BaseViewHolder<ServerPost>, position: Int) {
//        when (holder) {
//            is MainPostListViewHolder -> holder.onBindViewHolder(viewModel.postList[position], position)
//            is InfinitePostRecyclerViewProgressHolder -> holder.onBindViewHolder(null, position)
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int
//        = if (viewModel.postList.size > position) InfinitePostListViewHolderType.DEFALUT
//        else InfinitePostListViewHolderType.ROADING
//}