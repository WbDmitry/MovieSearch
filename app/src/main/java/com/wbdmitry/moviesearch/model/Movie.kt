package com.wbdmitry.moviesearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val poster: Int,
    val title: String,
    val description: String
) : Parcelable