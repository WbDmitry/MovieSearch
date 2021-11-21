package com.wbdmitry.moviesearch.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val poster: String?,
    val title: String?,
    val description: String?
) : Parcelable