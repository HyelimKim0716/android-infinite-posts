package com.riiid.infiniteposts.riiidpostlist.di

import com.riiid.infiniteposts.riiidpostlist.data.PostApi
import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailViewModel
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewModel
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
}