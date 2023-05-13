package io.github.z0r3f.weather.core.chat.domain

import io.github.z0r3f.weather.core.forecast.domain.Location

data class FavoriteLocation(
    var id: Long? = null,
    var chat: Chat? = null,
    var name: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var country: String? = null,
) {
    fun toLocation() = Location(
        name = name,
        latitude = latitude,
        longitude = longitude,
        country = country
    )
}
