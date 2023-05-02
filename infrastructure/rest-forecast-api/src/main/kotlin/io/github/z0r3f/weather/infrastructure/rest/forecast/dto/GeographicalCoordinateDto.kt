package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GeographicalCoordinateDto(
    @JsonProperty("name") val name: String,
    @JsonProperty("lat") val latitude: Double, // -33.86
    @JsonProperty("lon") val longitude: Double, // 151.21
    @JsonProperty("country") val country: String, // Country code (GB, JP etc.)
    @JsonProperty("local_names") val localNames: Map<String, String>? = null, // "ja": "シウダー・レアル"
)
