package com.afanasyeva656.onlinecinema.features.about_movie_screen.ui

import com.afanasyeva656.onlinecinema.base.BaseViewModel
import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

class AboutMovieViewModel(
    private val movieDomainModel: MovieDomainModel
): BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState {
        return ViewState(movieDomainModel, "")
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {

        }
        return null
    }
}