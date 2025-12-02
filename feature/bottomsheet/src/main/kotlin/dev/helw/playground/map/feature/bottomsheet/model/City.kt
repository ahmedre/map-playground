package dev.helw.playground.map.feature.bottomsheet.model

import androidx.annotation.StringRes

data class City(
    val id: Int,
    @param:StringRes val nameRes: Int,
    @param:StringRes val countryRes: Int,
    val latitude: Double,
    val longitude: Double
)
