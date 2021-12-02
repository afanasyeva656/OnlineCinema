package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

data class ViewState(
    val moviesList: List<MovieDomainModel>,
    val isLoading: Boolean,
    val error: Throwable?,
    val errorMessageForUser: Int?
)

sealed class UiEvent : Event {
    data class OnMovieClicked(val movieDomainModel: MovieDomainModel) : UiEvent()
    object OnTryAgainClicked : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadData : DataEvent()
    data class SuccessMoviesList(val moviesList: List<MovieDomainModel>, val isLoading: Boolean) :
        DataEvent()

    data class ErrorMoviesList(val error: Throwable, val isLoading: Boolean) : DataEvent()
    object OnResetData : DataEvent()
}