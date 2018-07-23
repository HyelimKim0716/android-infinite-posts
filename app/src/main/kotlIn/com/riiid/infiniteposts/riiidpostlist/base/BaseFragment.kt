package com.riiid.infiniteposts.riiidpostlist.base

import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {
    abstract fun refresh()
}