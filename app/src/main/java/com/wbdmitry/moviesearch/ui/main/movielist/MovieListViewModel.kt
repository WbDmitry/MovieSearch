package com.wbdmitry.moviesearch.ui.main.movielist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.repository.Repository
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import java.lang.Thread.sleep
import kotlin.random.Random

class MovieListViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel(),
    LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve
    fun getData() = getNewDataFromLocalSource()

    private fun getNewDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(MovieListViewModelConstants.SLEEPING_TIME.toLong())
            try {
                when (Random.nextInt(MovieListViewModelConstants.RANDOM_VALUE)) {
                    0 -> liveDataToObserve.postValue(
                        AppState.Success(
                            repository.getMoviesFromLocalStorageCategory1(),
                            repository.getMoviesFromLocalStorageCategory2()
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

    object MovieListViewModelConstants {
        const val SLEEPING_TIME = 2000
        const val RANDOM_VALUE = 2
    }
}