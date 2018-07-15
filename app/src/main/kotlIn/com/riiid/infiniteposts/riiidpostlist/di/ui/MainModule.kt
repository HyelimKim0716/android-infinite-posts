package com.riiid.infiniteposts.riiidpostlist.di.ui

import com.riiid.infiniteposts.riiidpostlist.ui.main.MainPostListAdapter
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePostRecyclerViewAdapter(viewModel: MainViewModel)
            = MainPostListAdapter(viewModel)
}