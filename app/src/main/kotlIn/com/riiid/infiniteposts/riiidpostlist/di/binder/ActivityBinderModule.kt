package com.riiid.infiniteposts.riiidpostlist.di.binder

import com.riiid.infiniteposts.riiidpostlist.di.ui.DetailModule
import com.riiid.infiniteposts.riiidpostlist.di.ui.MainModule
import com.riiid.infiniteposts.riiidpostlist.ui.detail.DetailActivity
import com.riiid.infiniteposts.riiidpostlist.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinderModule {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(DetailModule::class)])
    abstract fun bindDetailActivity(): DetailActivity
}