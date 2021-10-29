package com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api

import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

interface MoviesRepo {
    suspend fun getMoviesList(): List<MovieDomainModel>
}