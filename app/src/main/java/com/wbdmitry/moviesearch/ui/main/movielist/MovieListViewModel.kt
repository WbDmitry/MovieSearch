package com.wbdmitry.moviesearch.ui.main.movielist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.repository.Repository
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl

class MovieListViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel(),
    LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve
    fun getData() = getNewDataFromLocalSource()

    private fun getNewDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            try {
                when (0) {
                    0 -> liveDataToObserve.postValue(
                        AppState.Success(
                            repository.getMoviesCategory1FromServer(),
                            repository.getMoviesCategory2FromServer(),
                        )
                    )
                    1 -> throw Exception("Нет соединения с сервером!")
                    else -> throw Exception("Ошибка загрузки данных!")
                }
            } catch (ex: Exception) {
                liveDataToObserve.postValue(
                    AppState.Error(
                        error = ex
                    )
                )
            }
        }.start()
    }
}