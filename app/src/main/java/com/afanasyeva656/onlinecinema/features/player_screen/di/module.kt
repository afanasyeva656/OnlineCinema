package com.afanasyeva656.onlinecinema.features.player_screen.di

import com.google.android.exoplayer2.ExoPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val playerModule = module {

    factory<ExoPlayer> {
        ExoPlayer.Builder(androidContext())
            .build()
    }
}