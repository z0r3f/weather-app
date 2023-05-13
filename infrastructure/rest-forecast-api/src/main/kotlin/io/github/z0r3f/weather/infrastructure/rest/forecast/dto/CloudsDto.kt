package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CloudsDto(
    @JsonProperty("all")
    var all: Int = 0 // Cloudiness, %
)
