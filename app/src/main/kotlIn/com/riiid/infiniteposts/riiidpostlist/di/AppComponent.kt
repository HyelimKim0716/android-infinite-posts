package com.riiid.infiniteposts.riiidpostlist.di

import android.app.Application
import com.riiid.infiniteposts.riiidpostlist.base.BaseApplication
import com.riiid.infiniteposts.riiidpostlist.di.binder.ActivityBinderModule
import com.riiid.infiniteposts.riiidpostlist.di.binder.FragmentBindingModule
import com.riiid.infiniteposts.riiidpostlist.di.data.ApiModule
import com.riiid.infiniteposts.riiidpostlist.di.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
                                AppModule::class,
                                ApiModule::class,
                                RepositoryModule::class,
                                ActivityBinderModule::class,
                                FragmentBindingModule::class,
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

