package com.wbdmitry.moviesearch.model.entity

import android.os.Parcelable
import com.wbdmitry.moviesearch.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val poster: Int = 0,
    val title: String = "title",
    val description: String = "description"
) : Parcelable

fun getMovieListCategory1(): List<Movie> = listOf(
    Movie(R.drawable.ic_launcher_background, "Фильм 1", "Описание фильма 1"),
    Movie(R.drawable.ic_launcher_background, "Фильм 2", "Описание фильма 2"),
    Movie(R.drawable.ic_launcher_background, "Фильм 3", "Описание фильма 3"),
    Movie(R.drawable.ic_launcher_background, "Фильм 4", "Описание фильма 4"),
    Movie(R.drawable.ic_launcher_background, "Фильм 5", "Описание фильма 5"),
    Movie(R.drawable.ic_launcher_background, "Фильм 6", "Описание фильма 6"),
    Movie(R.drawable.ic_launcher_background, "Фильм 7", "Описание фильма 7"),
    Movie(R.drawable.ic_launcher_background, "Фильм 8", "Описание фильма 8")
)

fun getMovieListCategory2(): List<Movie> = listOf(
    Movie(R.drawable.ic_launcher_foreground, "1 Фильм", "1 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "2 Фильм", "2 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "3 Фильм", "3 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "4 Фильм", "4 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "5 Фильм", "5 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "6 Фильм", "6 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "7 Фильм", "7 Описание фильма"),
    Movie(R.drawable.ic_launcher_foreground, "8 Фильм", "8 Описание фильма")
)