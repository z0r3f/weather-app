package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CloudsDto(
    @JsonProperty("all")
    var all: Int = 0 // Cloudiness, %
)
