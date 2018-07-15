package com.riiid.infiniteposts.riiidpostlist.base

import com.riiid.infiniteposts.riiidpostlist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}