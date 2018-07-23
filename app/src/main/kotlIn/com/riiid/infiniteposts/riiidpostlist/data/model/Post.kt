package com.riiid.infiniteposts.riiidpostlist.data.model

import java.util.*

interface Post {
    var postId: String
    var userId: Int
    var id: Int
    var title: String
    var body: String
    var thumbnailUrl: String?
    var createdTime: Long

    fun initValue() {
        postId = UUID.randomUUID().toString()
        createdTime = getCurrentTime()
    }

    fun getCurrentTime() = System.currentTimeMillis()
}