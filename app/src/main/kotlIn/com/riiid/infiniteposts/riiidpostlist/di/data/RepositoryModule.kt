package com.riiid.infiniteposts.riiidpostlist.di.data

import android.content.Context
import com.riiid.infiniteposts.riiidpostlist.base.PostRepository
import com.riiid.infiniteposts.riiidpostlist.data.repository.PostRepositoryImpl
import com.riiid.infiniteposts.riiidpostlist.data.repository.RealmDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRealmDatabase(@Named("appContext") context: Context): RealmDatabase = RealmDatabase(context).apply { setup() }

    @Provides
    @Singleton
    fun provideRepositoryImpl(realmDatabase: RealmDatabase): PostRepository = PostRepositoryImpl(realmDatabase)
}