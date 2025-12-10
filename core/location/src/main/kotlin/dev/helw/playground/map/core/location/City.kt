package dev.helw.playground.map.core.location

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val id: Int,
    @param:StringRes val nameRes: Int,
    @param:StringRes val countryRes: Int,
    val latitude: Double,
    val longitude: Double
) : Parcelable