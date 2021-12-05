package com.wbdmitry.moviesearch.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    val results: List<Movie>
) : Parcelable