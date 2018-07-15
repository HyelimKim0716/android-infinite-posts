package com.riiid.infiniteposts.riiidpostlist.di

import android.app.Application
import com.riiid.infiniteposts.riiidpostlist.base.BaseApplication
import com.riiid.infiniteposts.riiidpostlist.di.binder.ActivityBinderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
                                AppModule::class,
                                ApiModule::class,
                                ActivityBinderModule::class,
                                ViewModelModule::class
        ))
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

