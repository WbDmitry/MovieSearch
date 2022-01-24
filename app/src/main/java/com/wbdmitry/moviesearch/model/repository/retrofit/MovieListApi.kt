package com.wbdmitry.moviesearch.model.repository.retrofit

import com.wbdmitry.moviesearch.model.entity.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieListApi {
    @GET("3/movie/{list_category}")
    fun getMoviesList(
        @Path("list_category") movieCategory: String,
        @Query("api_key") key: String,
        @Query("language") locale: String,
    ): Call<MovieList>
}