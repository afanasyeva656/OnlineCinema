package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import android.util.Log
import com.afanasyeva656.onlinecinema.base.BaseViewModel
import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.MoviesInteractor

class MoviesListViewModel(
    private val moviesInteractor: MoviesInteractor
) : BaseViewModel<ViewState>() {
    init {
        processDataEvent(DataEvent.OnLoadData)
    }

    override fun initialViewState(): ViewState {
        return ViewState(emptyList(), true, "")
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
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