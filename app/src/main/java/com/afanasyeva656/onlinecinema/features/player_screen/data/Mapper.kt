package com.afanasyeva656.onlinecinema.features.player_screen.data

import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.afanasyeva656.onlinecinema.features.player_screen.data.local.MovieEntity

fun MovieDomainModel.toEntity(playbackPosition: Long) = MovieEntity(
    id = id,
    originalTitle = originalTitle,
    title = title,
    video = video,
    playbackPosition = playbackPosition
)

