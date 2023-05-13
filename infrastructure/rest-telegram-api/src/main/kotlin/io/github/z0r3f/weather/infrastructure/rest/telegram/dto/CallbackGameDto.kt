package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CallbackGameDto(
    @JsonProperty("game_short_name") val gameShortName: String
)
