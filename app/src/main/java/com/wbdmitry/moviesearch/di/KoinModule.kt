package com.wbdmitry.moviesearch.di

import com.wbdmitry.moviesearch.model.repository.Repository
import com.wbdmitry.moviesearch.model.repository.RepositoryImpl
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MovieListViewModel(get()) }
}