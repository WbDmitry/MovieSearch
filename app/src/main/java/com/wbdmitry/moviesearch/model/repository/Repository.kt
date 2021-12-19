package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.entity.History
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.entity.MovieList
import retrofit2.Callback

interface Repository {
    fun getMovieListFromServer(
        callback: Callback<MovieList>
    )

    fun getMovieInfoFromServer(
        id: Int,
        callback: Callback<Movie>
    )

    fun saveToHistory(history: History)

    fun getAllHistory(): List<History>
}