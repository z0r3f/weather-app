package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherDto (
    @JsonProperty("id")
    // Weather condition id.
    val id: Int,

    @JsonProperty("main")
    // Group of weather parameters (Rain, Snow, Extreme etc.)
    val main: String,

    @JsonProperty("description")
    // Weather condition within the group.
    val description: String,

    @JsonProperty("icon")
    // Weather icon id.
    val icon: String
)
