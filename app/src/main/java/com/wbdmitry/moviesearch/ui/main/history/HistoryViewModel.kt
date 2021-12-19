package com.wbdmitry.moviesearch.ui.main.history

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wbdmitry.moviesearch.model.entity.History
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.model.repository.retrofit.RemoteDataSource

class HistoryViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource())
) : ViewModel(), LifecycleObserver {
    val historyLiveData: MutableLiveData<List<History>> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.postValue(repository.getAllHistory())
    }
}