package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CoordinateDto(
    @JsonProperty("lat") val latitude: Double, // -33.86
    @JsonProperty("lon") val longitude: Double // 151.21
)
