package com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api

import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.toDomain
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

class MoviesRepoImpl(private val source: MoviesRemoteSource): MoviesRepo {
    override suspend fun getMoviesList(): List<MovieDomainModel> {
        return source.getMoviesList().map {it.toDomain()}
    }
}