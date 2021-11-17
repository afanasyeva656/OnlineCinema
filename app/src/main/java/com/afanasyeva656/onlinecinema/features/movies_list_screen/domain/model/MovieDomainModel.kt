package com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDomainModel(
    val adult: Boolean,
    val genres: List<GenreDomainModel>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val popularity: Double,
    val title: String,
    val video: String,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable