package io.github.z0r3f.weather.core.forecast.domain

import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation

data class Location(
    val name: String? = null,
    val country: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val population: Long? = null,
    val timezone: Int? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
) {
    fun isInvalid() = (latitude == null) || latitude.isNaN() || (longitude == null) || longitude.isNaN()

    fun toFavoriteLocation() = FavoriteLocation(
        name = name,
        country = country,
        latitude = latitude,
        longitude = longitude,
    )
}
