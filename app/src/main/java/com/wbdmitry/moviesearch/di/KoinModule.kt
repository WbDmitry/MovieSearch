package com.wbdmitry.moviesearch.di

import com.wbdmitry.moviesearch.model.repository.Repository
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.ui.main.history.HistoryViewModel
import com.wbdmitry.moviesearch.ui.main.movieInfo.MovieInfoViewModel
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }

    viewModel { MovieListViewModel(get()) }
    viewModel { MovieInfoViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}