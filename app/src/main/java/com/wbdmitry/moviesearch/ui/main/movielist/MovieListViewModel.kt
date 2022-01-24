package com.wbdmitry.moviesearch.ui.main.movielist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.entity.MovieList
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.model.repository.retrofit.REQUEST_ERROR
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import com.wbdmitry.moviesearch.model.repository.retrofit.SERVER_ERROR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource()),
) : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {
    val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getNewDataFromServer() {
        launch {
            liveData.value = AppState.Loading
            repository.getMovieListFromServer(callback)
        }
    }

    private val callback = object :
        Callback<MovieList> {
        override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
            val serverResponse: MovieList? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieList>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: MovieList): AppState {
            return AppState.Success(serverResponse.results)
        }
    }
}