package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RainDto(
    @JsonProperty("3h")
    val `3h`: Double? // Rain volume for the last 3 hours
)
