package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import com.afanasyeva656.onlinecinema.R
import com.afanasyeva656.onlinecinema.base.BaseViewModel
import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.base.navigation.Screens
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.MoviesInteractor
import com.github.terrakok.cicerone.Router
import java.net.UnknownHostException

class MoviesListViewModel(
    private val moviesInteractor: MoviesInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {
    init {
        processDataEvent(DataEvent.OnLoadData)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            moviesList = emptyList(),
            isLoading = true,
            errorMessageForUser = null,
            error = null
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnMovieClicked -> {
                val screen = Screens.AboutMovieScreen(movieDomainModel = event.movieDomainModel)
                router.navigateTo(screen)
            }
            is UiEvent.OnTryAgainClicked -> {
//                processDataEvent(DataEvent.OnResetData)
                processDataEvent(DataEvent.OnLoadData)
            }
            is DataEvent.OnLoadData -> {
                moviesInteractor.getMoviesList().fold(
                    onSuccess = { processDataEvent(DataEvent.SuccessMoviesList(it, false)) },
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorMoviesList(
                                error = it, isLoading = false
                            )
                        )
                    }
                )
            }
            is DataEvent.SuccessMoviesList -> {
                return previousState.copy(
                    moviesList = event.moviesList,
                    isLoading = event.isLoading,
                    errorMessageForUser = null,
                    error = null
                )
            }
            is DataEvent.ErrorMoviesList -> {
                return previousState.copy(
                    error = event.error,
                    isLoading = event.isLoading,
                    errorMessageForUser = defineError(event.error)
                )
            }
            is DataEvent.OnResetData -> {
                return previousState.copy(
                    moviesList = emptyList(),
                    isLoading = true,
                    error = null,
                    errorMessageForUser = null
                )
            }
        }
        return null
    }

    private fun defineError(error: Throwable): Int {
        return when (error) {
            is UnknownHostException -> R.string.internet_connection_error
            else -> R.string.unknown_error
        }
    }
}