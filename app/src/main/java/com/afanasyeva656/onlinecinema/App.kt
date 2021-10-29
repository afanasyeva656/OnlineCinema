package com.afanasyeva656.onlinecinema

import android.app.Application
import com.afanasyeva656.onlinecinema.di.appModule
import com.afanasyeva656.onlinecinema.features.movies_list_screen.di.movieListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, movieListModule)
        }
    }
}