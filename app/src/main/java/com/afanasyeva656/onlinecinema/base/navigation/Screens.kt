package com.afanasyeva656.onlinecinema.base.navigation

import com.afanasyeva656.onlinecinema.features.about_movie_screen.ui.AboutMovieFragment
import com.afanasyeva656.onlinecinema.features.movies_list_screen.ui.MoviesListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun MoviesListScreen() = FragmentScreen { MoviesListFragment.newInstance() }
    fun AboutMovieScreen() = FragmentScreen { AboutMovieFragment.newInstance() }
// Factory
//    fun SomeScreen() = FragmentScreen { factory: FragmentFactory -> ... }
}