package com.privalia.test.screens.movies.list

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.util.DiffUtil
import com.zeyad.gadapter.ItemInfo
import kotlinx.android.parcel.Parcelize

sealed class MovieListState(val movies: List<ItemInfo> = emptyList(),
                            val page: Int = 1,
                            val callback: DiffUtil.DiffResult =
                                    DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf(), mutableListOf()))) : Parcelable

@Parcelize
data class EmptyState(val ePage: Int = 1) : MovieListState(), Parcelable

data class MovieSearchState(val sMovies: List<ItemInfo> = emptyList(),
                            val sCallback: DiffUtil.DiffResult =
                                    DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf(), mutableListOf())),
                            val sPage: Int = 1) : MovieListState(page = sPage, callback = sCallback), Parcelable {

    constructor(parcel: Parcel) : this(
            emptyList(),
            DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf<ItemInfo>(), mutableListOf<ItemInfo>())),
            parcel.readInt())

    fun isEmpty() = movies.isEmpty()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sPage)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<MovieSearchState> {
        override fun createFromParcel(parcel: Parcel) = MovieSearchState(parcel)

        override fun newArray(size: Int) = arrayOfNulls<MovieSearchState?>(size)
    }
}

data class GetMovieState(val gMovies: List<ItemInfo> = emptyList(),
                            val gCallback: DiffUtil.DiffResult =
                                    DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf(), mutableListOf())),
                            val gPage: Int = 1) : MovieListState(page = gPage, callback = gCallback), Parcelable {

    constructor(parcel: Parcel) : this(
            emptyList(),
            DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf<ItemInfo>(), mutableListOf<ItemInfo>())),
            parcel.readInt())

    fun isEmpty() = movies.isEmpty()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(gPage)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<GetMovieState> {
        override fun createFromParcel(parcel: Parcel) = GetMovieState(parcel)

        override fun newArray(size: Int) = arrayOfNulls<GetMovieState?>(size)
    }
}
