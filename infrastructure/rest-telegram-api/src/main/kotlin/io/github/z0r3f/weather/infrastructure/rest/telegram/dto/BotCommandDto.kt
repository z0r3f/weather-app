package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BotCommandDto(
    @JsonProperty("command") val command: String,
    @JsonProperty("description") val description: String,
)
