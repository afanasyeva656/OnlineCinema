package com.afanasyeva656.onlinecinema.features.movies_list_screen.data

import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.model.GenreModel
import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.model.MovieModel
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.GenreDomainModel
import com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model.MovieDomainModel

fun MovieModel.toDomain() = MovieDomainModel(
    adult = this.adult,
    genres = this.genres.map { it.toDomain() },
    id = this.id,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterPath = this.posterPath,
    popularity = this.popularity,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun GenreModel.toDomain() = GenreDomainModel(name = this.name)

