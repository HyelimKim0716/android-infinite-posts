package com.riiid.infiniteposts.riiidpostlist

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SchedulersRule: TestRule {

    val scheduler = Schedulers.trampoline()

    override fun apply(base: Statement?, description: Description?): Statement {

        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { scheduler }
                RxJavaPlugins.setComputationSchedulerHandler { scheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }

                try {
                    base?.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }

            }

        }
    }
}