package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import com.afanasyeva656.onlinecinema.base.BaseViewModel
import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.base.navigation.Screens
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.MoviesInteractor
import com.github.terrakok.cicerone.Router

class MoviesListViewModel(
    private val moviesInteractor: MoviesInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {
    init {
        processDataEvent(DataEvent.OnLoadData)
    }

    override fun initialViewState(): ViewState {
        return ViewState(emptyList(), true, "")
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnMovieClicked -> {
                val screen = Screens.AboutMovieScreen(movieDomainModel = event.movieDomainModel)
                router.navigateTo(screen)
            }
            is DataEvent.OnLoadData -> {
                moviesInteractor.getMoviesList().fold(
                    onSuccess = { processDataEvent(DataEvent.SuccessMoviesList(it, false)) },
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorMoviesList(
                                it.localizedMessage ?: "error", false
                            )
                        )
                    }
                )
            }
            is DataEvent.SuccessMoviesList -> {
                return previousState.copy(
                    moviesList = event.moviesList,
                    isLoading = event.isLoading
                )
            }
            is DataEvent.ErrorMoviesList -> {
                return previousState.copy(
                    errorMessage = event.errorMessage,
                    isLoading = event.isLoading
                )
            }
        }
        return null
    }

}