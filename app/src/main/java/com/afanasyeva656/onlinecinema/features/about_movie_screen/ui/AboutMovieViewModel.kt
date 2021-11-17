package com.afanasyeva656.onlinecinema.features.about_movie_screen.ui

import com.afanasyeva656.onlinecinema.base.BaseViewModel
import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.base.navigation.Screens
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel
import com.github.terrakok.cicerone.Router

class AboutMovieViewModel(
    private val movieDomainModel: MovieDomainModel,
    private val router: Router
): BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        return ViewState(movieDomainModel, "")
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {
            is UiEvent.OnVideoClicked -> {
                val screen = Screens.PlayerScreen(movieDomainModel.video)
                router.navigateTo(screen)
            }
        }
        return null
    }
}