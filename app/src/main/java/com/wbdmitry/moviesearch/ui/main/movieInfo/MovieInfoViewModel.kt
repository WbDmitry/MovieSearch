package com.wbdmitry.moviesearch.ui.main.movieInfo

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wbdmitry.moviesearch.model.AppState
import com.wbdmitry.moviesearch.model.entity.Movie
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.model.repository.retrofit.CORRUPTED_DATA
import com.wbdmitry.moviesearch.model.repository.retrofit.REQUEST_ERROR
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource
import com.wbdmitry.moviesearch.model.repository.retrofit.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInfoViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource())
) : ViewModel(), LifecycleObserver {
    val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMovieInfoFromRemoteSource(id: Int) {
        liveData.value = AppState.Loading
        repository.getMovieInfoFromServer(id, callback)
    }

    private val callback = object : Callback<Movie> {
        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
            val serverResponse: Movie? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Movie>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: Movie): AppState {
            return if (serverResponse.title == null) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(listOf(serverResponse))
            }
        }
    }
}