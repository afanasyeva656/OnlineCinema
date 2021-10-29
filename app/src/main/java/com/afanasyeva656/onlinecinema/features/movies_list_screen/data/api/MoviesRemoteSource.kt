package com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api

import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.model.MovieResultsModel

class MoviesRemoteSource(private val api: MoviesApi) {
    suspend fun getMoviesList(): MovieResultsModel {
        return api.getMoviesList()
    }
}