package com.afanasyeva656.onlinecinema.features.movies_list_screen.domain

import com.afanasyeva656.onlinecinema.base.attempt
import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.MoviesRepo

class MoviesInteractor(private val repo: MoviesRepo) {
    suspend fun getMoviesList() = attempt { repo.getMoviesList() }
}