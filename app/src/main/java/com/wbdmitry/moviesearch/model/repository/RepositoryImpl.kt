package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.entity.getMovieListCategory1
import com.wbdmitry.moviesearch.model.entity.getMovieListCategory2

class RepositoryImpl : Repository {
    override fun getMoviesFromServer(): Movie {
        return Movie()
    }

    override fun getMoviesFromLocalStorageCategory1() = getMovieListCategory1()

    override fun getMoviesFromLocalStorageCategory2() = getMovieListCategory2()
}