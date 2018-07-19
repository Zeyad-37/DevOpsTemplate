package com.privalia.test.screens.movies.list

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.util.DiffUtil
import com.zeyad.gadapter.ItemInfo

data class MovieListState(val movies: List<ItemInfo> = emptyList(),
                          val callback: DiffUtil.DiffResult = DiffUtil.calculateDiff
                          (MovieDiffCallBack(mutableListOf<ItemInfo>(), mutableListOf<ItemInfo>())),
                          val page: Int = 1,
                          var currentFragTag: String = "",
                          val isTwoPane: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
            emptyList(),
            DiffUtil.calculateDiff
            (MovieDiffCallBack(mutableListOf<ItemInfo>(), mutableListOf<ItemInfo>())),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(currentFragTag)
        parcel.writeByte(if (isTwoPane) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun isEmpty(): Boolean {
        return movies.isEmpty()
    }

    companion object CREATOR : Parcelable.Creator<MovieListState> {
        override fun createFromParcel(parcel: Parcel): MovieListState {
            return MovieListState(parcel)
        }

        override fun newArray(size: Int): Array<MovieListState?> {
            return arrayOfNulls(size)
        }
    }
}
