package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RainDto(
    @JsonProperty("3h") val `3h`: Double? = null, // Rain volume for the last 3 hours
    @JsonProperty("1h") val `1h`: Double? = null, // Rain volume for the last 1 hour
)
