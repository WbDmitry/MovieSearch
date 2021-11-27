package com.wbdmitry.moviesearch.model.restEntity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDTO(
    val id: Int,
    val poster_path: String?,
    val title: String?,
    val overview: String?
) : Parcelable

