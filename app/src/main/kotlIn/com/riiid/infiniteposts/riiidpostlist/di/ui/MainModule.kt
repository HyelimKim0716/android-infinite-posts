package com.riiid.infiniteposts.riiidpostlist.di.ui

import com.riiid.infiniteposts.riiidpostlist.ui.main.favorite_post.FavoritePostFragment
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostFragment
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideInfinitePostFragment() = InfinitePostFragment()

    @Provides
    fun provideFavoritePostFragment() = FavoritePostFragment()
}