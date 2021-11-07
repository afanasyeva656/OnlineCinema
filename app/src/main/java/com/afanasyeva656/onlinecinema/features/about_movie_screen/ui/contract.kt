package com.afanasyeva656.onlinecinema.features.about_movie_screen.ui

import com.afanasyeva656.onlinecinema.base.Event
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

data class ViewState(
    val movieDomainModel: MovieDomainModel,
    val errorMessage: String
)

sealed class UiEvent : Event {
    object OnVideoClicked: UiEvent()
    object OnBackClicked: UiEvent()
}