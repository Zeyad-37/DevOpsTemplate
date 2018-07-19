package com.privalia.test.screens.movies.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResponse(@SerializedName("total_pages") val totalPages: Int,
                             @SerializedName("sort_by") val sortBy: String,
                             @SerializedName("total_results") val totalResults: Int,
                             val name: String,
                             val id: Int,
                             val page: Int,
                             val results: List<Movie> = emptyList()) : Parcelable
