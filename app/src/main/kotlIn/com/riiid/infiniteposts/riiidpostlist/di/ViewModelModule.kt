package com.riiid.infiniteposts.riiidpostlist.di

import com.riiid.infiniteposts.riiidpostlist.base.PostRepository
import com.riiid.infiniteposts.riiidpostlist.data.api.PhotoApi
import com.riiid.infiniteposts.riiidpostlist.data.api.PostApi
import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailViewModel
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewModel
import com.riiid.infiniteposts.riiidpostlist.ui.main.favorite_post.FavoritePostViewModel
import com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post.InfinitePostViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideMainViewModel(postApi: PostApi) = MainViewModel(postApi)

    @Provides
    @Singleton
    fun provideDetailViewModel(postApi: PostApi) = DetailViewModel(postApi)

    @Provides
    @Singleton
    fun provideInfinitePostViewModel(postApi: PostApi, photoApi: PhotoApi, postRepository: PostRepository)
            = InfinitePostViewModel(postApi, photoApi, postRepository)

    @Provides
    @Singleton
    fun provideFavoritePostViewModel(postRepository: PostRepository) = FavoritePostViewModel(postRepository)

}