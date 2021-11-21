package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.MovieLoader
import com.wbdmitry.moviesearch.model.entity.Movie

class RepositoryImpl : Repository {
    override fun getMoviesFromServer(id: Int): Movie {
        val dto = MovieLoader.loadMovie(id)
        return Movie(
            id = dto?.id ?: 0,
            poster = dto?.poster_path ?: "",
            title = dto?.title ?: "",
            description = dto?.overview ?: "",
        )
    }

    override fun getMoviesCategory1FromServer(): List<Movie> {
        val dto = MovieLoader.loadListMoviesCategory1()
        val listMovies = mutableListOf<Movie>()
        dto?.results?.forEach {
            listMovies += Movie(
                id = it.id,
                poster = it.poster_path,
                title = it.title,
                description = it.overview,
            )
        }
        return listMovies
    }

    override fun getMoviesCategory2FromServer(): List<Movie> {
        val dto = MovieLoader.loadListMoviesCategory2()
        val listMovies = mutableListOf<Movie>()
        dto?.results?.forEach {
            listMovies += Movie(
                id = it.id,
                poster = it.poster_path,
                title = it.title,
                description = it.overview,
            )
        }
        return listMovies
    }
}