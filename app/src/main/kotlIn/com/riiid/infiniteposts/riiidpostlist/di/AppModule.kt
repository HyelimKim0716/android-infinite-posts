package com.riiid.infiniteposts.riiidpostlist.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @Named("appContext")
    fun provideContext(application: Application): Context = application.applicationContext

}