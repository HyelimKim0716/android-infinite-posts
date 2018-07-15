package com.riiid.infiniteposts.riiidpostlist.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseBindingActivity<BINDING: ViewDataBinding>: DaggerAppCompatActivity() {

    lateinit var binding: BINDING
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

}