package com.afanasyeva656.onlinecinema.features.player_screen.domain.model

data class WatchedMovieDomainModel(
    val id: Int,
    val originalTitle: String,
    val title: String,
    val video: String,
    val playbackPosition: Long
    )
