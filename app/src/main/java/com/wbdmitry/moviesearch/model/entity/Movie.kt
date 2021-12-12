package com.wbdmitry.moviesearch.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val adult: Boolean
) : Parcelable
