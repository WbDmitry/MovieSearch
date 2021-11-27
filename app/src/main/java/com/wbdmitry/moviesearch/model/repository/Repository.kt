package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.entity.Movie

interface Repository {
    fun getMoviesFromServer(id: Int): Movie
    fun getMoviesCategory1FromServer(): List<Movie>
    fun getMoviesCategory2FromServer(): List<Movie>
}