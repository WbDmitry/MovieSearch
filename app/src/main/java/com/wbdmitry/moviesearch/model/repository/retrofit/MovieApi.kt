package com.wbdmitry.moviesearch.model.repository.retrofit

import com.wbdmitry.moviesearch.model.entity.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") locale: String,
    ): Call<Movie>
}