package com.wbdmitry.moviesearch.model.restEntity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovieDTO(
    val results: List<MovieDTO>
) : Parcelable