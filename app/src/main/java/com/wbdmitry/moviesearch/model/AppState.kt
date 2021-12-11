package com.wbdmitry.moviesearch.model

import com.wbdmitry.moviesearch.model.entity.Movie

sealed class AppState {
    data class Success(val movieData: List<Movie>) : AppState()

    data class Error(val error: Throwable) : AppState()

    object Loading : AppState()
}
