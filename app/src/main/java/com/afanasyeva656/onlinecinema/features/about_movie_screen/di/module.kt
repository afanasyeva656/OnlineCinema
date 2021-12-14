package com.afanasyeva656.onlinecinema.features.about_movie_screen.di

import com.afanasyeva656.onlinecinema.features.about_movie_screen.ui.AboutMovieViewModel
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aboutMovieModule = module {
    viewModel<AboutMovieViewModel> { (movieModel: MovieDomainModel) ->
        AboutMovieViewModel(movieModel, get<Cicerone<Router>>().router)
    }
}