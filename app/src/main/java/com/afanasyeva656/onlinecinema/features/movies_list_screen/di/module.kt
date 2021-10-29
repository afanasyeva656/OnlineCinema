package com.afanasyeva656.onlinecinema.features.movies_list_screen.di

import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.MoviesApi
import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.MoviesRemoteSource
import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.MoviesRepo
import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.MoviesRepoImpl
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.MoviesInteractor
import com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val movieListModule = module {
    single<MoviesApi> {
        get<Retrofit>().create(MoviesApi::class.java)
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get<MoviesApi>())
    }

    single<MoviesRepo> {
        MoviesRepoImpl(get<MoviesRemoteSource>())
    }

    single<MoviesInteractor> {
        MoviesInteractor(get<MoviesRepo>())
    }

    viewModel<MoviesListViewModel> {
        MoviesListViewModel(get<MoviesInteractor>())
    }
}