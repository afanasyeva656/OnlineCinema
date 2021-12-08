package com.afanasyeva656.onlinecinema.features.player_screen.data

import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

interface WatchedMovieRepo {
    suspend fun create(movieDomainModel: MovieDomainModel)
    suspend fun update(movieDomainModel: MovieDomainModel)
}