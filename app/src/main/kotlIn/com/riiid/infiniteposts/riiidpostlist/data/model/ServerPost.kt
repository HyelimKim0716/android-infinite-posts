package com.riiid.infiniteposts.riiidpostlist.data.model

import android.os.Parcel
import android.os.Parcelable

data class ServerPost(val userId: Int = -1,
                      val id: Int = -1,
                      val title: String = "N/A",
                      val body: String = "N/A"): Parcelable, Comparable<ServerPost> {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(userId)
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(body)
    }

    override fun describeContents(): Int = id

    companion object CREATOR : Parcelable.Creator<ServerPost> {
        override fun createFromParcel(parcel: Parcel): ServerPost {
            return ServerPost(parcel)
        }

        override fun newArray(size: Int): Array<ServerPost?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: ServerPost): Int {
        return title.toLowerCase().compareTo(other.title.toLowerCase())
    }


}