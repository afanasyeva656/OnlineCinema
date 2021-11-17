package com.afanasyeva656.onlinecinema.features.movies_list_screen.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreDomainModel(
    val name: String
) : Parcelable