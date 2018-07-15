package com.riiid.infiniteposts.riiidpostlist.di.ui

import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailCommentListAdapter
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    fun provideCommentRecyclerViewAdapter() = DetailCommentListAdapter()

}