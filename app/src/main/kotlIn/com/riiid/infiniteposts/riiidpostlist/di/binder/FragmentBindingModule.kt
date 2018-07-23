package com.riiid.infiniteposts.riiidpostlist.di.binder

import com.riiid.infiniteposts.riiidpostlist.di.ui.FavoritePostModule
import com.riiid.infiniteposts.riiidpostlist.di.ui.InfinitePostModule
import com.riiid.infiniteposts.riiidpostlist.ui.main.favorite_post.FavoritePostFragment
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [(InfinitePostModule::class)])
    abstract fun provideInfinitePostFragment(): InfinitePostFragment

    @ContributesAndroidInjector(modules = [(FavoritePostModule::class)])
    abstract fun provideFavoritePostFragment(): FavoritePostFragment
}