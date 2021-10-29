package com.afanasyeva656.onlinecinema.features.movies_list_screen.ui

import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

data class ViewState(
    val moviesList: List<MovieDomainModel>,
    val isLoading: Boolean,
    val errorMessage: String
)

sealed class UiEvent : Event {

}

sealed class DataEvent : Event {
    object OnLoadData : DataEvent()
    data class SuccessMoviesList(val moviesList: List<MovieDomainModel>, val isLoading: Boolean) :
        DataEvent()

    data class ErrorMoviesList(val errorMessage: String, val isLoading: Boolean) : DataEvent()
}