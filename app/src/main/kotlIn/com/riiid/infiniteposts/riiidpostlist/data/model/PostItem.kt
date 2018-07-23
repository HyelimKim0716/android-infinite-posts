package com.riiid.infiniteposts.riiidpostlist.data.model

import android.os.Parcel
import android.os.Parcelable

data class PostItem(override var userId: Int = -1,
                    override var id: Int = -1,
                    override var title: String = "",
                    override var body: String = "",
                    override var thumbnailUrl: String ?= "") : Post, Parcelable {

    override lateinit var postId: String
    override var createdTime: Long = -1

    init {
        initValue()
    }


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
        postId = parcel.readString()
        createdTime = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeString(thumbnailUrl)
        parcel.writeString(postId)
        parcel.writeLong(createdTime)
    }

    override fun describeContents(): Int = id

    companion object CREATOR : Parcelable.Creator<PostItem> {
        override fun createFromParcel(parcel: Parcel): PostItem {
            return PostItem(parcel)
        }

        override fun newArray(size: Int): Array<PostItem?> {
            return arrayOfNulls(size)
        }
    }


}