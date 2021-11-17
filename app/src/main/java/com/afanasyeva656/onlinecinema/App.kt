package com.afanasyeva656.onlinecinema

import android.app.Application
import com.afanasyeva656.onlinecinema.di.appModule
import com.afanasyeva656.onlinecinema.di.navigationModule
import com.afanasyeva656.onlinecinema.features.about_movie_screen.di.aboutMovieModule
import com.afanasyeva656.onlinecinema.features.movies_list_screen.di.movieListModule
import com.afanasyeva656.onlinecinema.features.player_screen.di.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, navigationModule, movieListModule, playerModule, aboutMovieModule)
        }
    }
}