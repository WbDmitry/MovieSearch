package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.entity.MovieList
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override fun getMovieListFromServer(callback: Callback<MovieList>) {
        remoteDataSource.getMovieList(callback)
    }

    override fun getMovieInfoFromServer(
        id: Int,
        callback: Callback<Movie>
    ) {
        remoteDataSource.getMovieInfo(id, callback)
    }
}