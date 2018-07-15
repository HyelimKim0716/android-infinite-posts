package com.riiid.infiniteposts.riiidpostlist.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup

abstract class BaseDataBindingViewHolder<BINDING: ViewDataBinding, in ITEM>(parent: ViewGroup, itemView: View)
    : BaseViewHolder<ITEM>(parent, itemView) {

    protected var binding: BINDING ?= DataBindingUtil.bind(itemView)
}