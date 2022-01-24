package com.wbdmitry.moviesearch.model.repository.retrofit

import com.google.gson.GsonBuilder
import com.wbdmitry.moviesearch.BuildConfig
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.entity.MovieList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/"
const val LOCALE = "ru-Ru"
const val CATEGORY = "top_rated"
const val SERVER_ERROR = "Ошибка сервера"
const val REQUEST_ERROR = "Ошибка запроса на сервер"
const val CORRUPTED_DATA = "Неполные данные"

class RemoteDataSource {

    private val movieApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieApi::class.java)

    private val movieListApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieListApi::class.java)

    fun getMovieInfo(id: Int, callback: Callback<Movie>) {
        movieApi.getMovie(id, BuildConfig.MOVIE_API_KEY, LOCALE)
            .enqueue(callback)
    }

    fun getMovieList(callback: Callback<MovieList>) {
        movieListApi.getMoviesList(CATEGORY, BuildConfig.MOVIE_API_KEY, LOCALE)
            .enqueue(callback)
    }
}
