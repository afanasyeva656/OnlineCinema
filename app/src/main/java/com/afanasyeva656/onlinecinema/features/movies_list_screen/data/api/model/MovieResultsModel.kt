package com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieResultsModel(
    @SerializedName("results") val results: List<MovieModel>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)
