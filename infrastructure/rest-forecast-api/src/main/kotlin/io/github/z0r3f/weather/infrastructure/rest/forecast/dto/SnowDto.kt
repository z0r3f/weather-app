package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SnowDto (
    @JsonProperty("3h")
    val `3h`: Double? = null // 3h snow volume for the last 3 hours
)
