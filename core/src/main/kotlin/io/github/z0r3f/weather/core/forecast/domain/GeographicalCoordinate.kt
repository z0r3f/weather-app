package io.github.z0r3f.weather.core.forecast.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class GeographicalCoordinate(
    @JsonProperty("name") val name: String,
    @JsonProperty("lat") val latitude: Double, // -33.86
    @JsonProperty("lon") val longitude: Double, // 151.21
    @JsonProperty("country") val country: String, // Country code (GB, JP etc.)
) {
    fun toLocation(): Location {
        return Location(
            name = name,
            latitude = latitude,
            longitude = longitude,
            country = country,
        )
    }
}
