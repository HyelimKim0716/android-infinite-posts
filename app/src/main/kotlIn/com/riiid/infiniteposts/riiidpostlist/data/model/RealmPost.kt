package com.riiid.infiniteposts.riiidpostlist.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmPost(@PrimaryKey override var postId: String = "") : Post, RealmObject() {
    override var userId: Int = -1
    override var id: Int = -1
    override lateinit var title: String
    override lateinit var body: String
    override var thumbnailUrl: String? = null
    override var createdTime: Long = -1

    init {
        initValue()
    }
}