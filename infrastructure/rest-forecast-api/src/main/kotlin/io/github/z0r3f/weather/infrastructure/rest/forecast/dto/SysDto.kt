package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SysDto(
    @JsonProperty("type") val type: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("country") val country: String,
    @JsonProperty("sunrise") val sunrise: Long,
    @JsonProperty("sunset") val sunset: Long,
)
