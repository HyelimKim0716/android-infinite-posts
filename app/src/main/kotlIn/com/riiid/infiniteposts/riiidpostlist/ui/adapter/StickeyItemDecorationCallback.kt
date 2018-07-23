package com.riiid.infiniteposts.riiidpostlist.ui.adapter

interface StickyItemDecorationCallback {
    fun isSection(position: Int): Boolean

    fun getSectionHeader(position: Int): CharSequence
}