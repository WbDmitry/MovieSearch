package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.entity.Movie

interface Repository {
    fun getMoviesFromServer(): Movie

    fun getMoviesFromLocalStorageCategory1(): List<Movie>

    fun getMoviesFromLocalStorageCategory2(): List<Movie>
}