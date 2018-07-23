package com.riiid.infiniteposts.riiidpostlist.di.ui

import com.riiid.infiniteposts.riiidpostlist.ui.adapter.StickyItemDecoration
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostViewModel
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.infinite_post_list.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class InfinitePostModule {

    @Provides
    fun provideInfinitePostRecyclerViewAdapter(viewModel: InfinitePostViewModel, viewHolderFactories: Map<Int, @JvmSuppressWildcards InfinitePostBaseRecyclerViewHolderFactory>)
    = InfinitePostRecyclerViewAdapter(viewModel, viewHolderFactories)

    @Provides
    @IntoMap
    @IntKey(InfinitePostListViewHolderType.DEFALUT)
    fun provideInfinitePostRecyclerViewHolder(viewModelProvider: Provider<InfinitePostViewModel>)
            : InfinitePostBaseRecyclerViewHolderFactory
    = InfinitePostRecyclerViewContentHolderFactory(viewModelProvider)

    @Provides
    @IntoMap
    @IntKey(InfinitePostListViewHolderType.ROADING)
    fun provideInfinitePostProgressViewHolder(): InfinitePostBaseRecyclerViewHolderFactory
    = InfinitePostRecyclerViewProgressHolderFactory()

    @Provides
    fun provideItemDecoration(): StickyItemDecoration = StickyItemDecoration()
}