package com.wbdmitry.moviesearch.model.repository

import com.wbdmitry.moviesearch.model.entity.History
import com.wbdmitry.moviesearch.model.entity.HistoryEntity
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.entity.MovieList
import com.wbdmitry.moviesearch.model.impl.Database
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import retrofit2.Callback

private const val DEFAULT_ID = 0L

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override fun getMovieListFromServer(callback: Callback<MovieList>) {
        remoteDataSource.getMovieList(callback)
    }

    override fun getMovieInfoFromServer(
        id: Int,
        callback: Callback<Movie>
    ) {
        remoteDataSource.getMovieInfo(id, callback)
    }

    override fun saveToHistory(history: History) {
        Database.db.historyDao().insert(convertHistoryToEntity(history))
    }

    override fun getAllHistory(): List<History> {
        return convertHistoryEntityToHistory(Database.db.historyDao().all())
    }

    private fun convertHistoryEntityToHistory(entityList: List<HistoryEntity>): List<History> =
        entityList.map {
            History(it.movie_id, it.time, it.movie_title)
        }

    private fun convertHistoryToEntity(history: History): HistoryEntity =
        HistoryEntity(
            DEFAULT_ID,
            history.movie_id,
            history.time,
            history.movie_title
        )
}