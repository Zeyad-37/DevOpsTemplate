package com.privalia.test.screens.movies.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(@SerializedName("overview") val overview: String = "",
                 @SerializedName("original_title") val originalTitle: String = "",
                 @SerializedName("title") val title: String = "",
                 @SerializedName("release_date") val releaseDate: String = "",
                 @SerializedName("poster_path") val posterPath: String,
                 @SerializedName("id") val id: Int = -1) : Parcelable