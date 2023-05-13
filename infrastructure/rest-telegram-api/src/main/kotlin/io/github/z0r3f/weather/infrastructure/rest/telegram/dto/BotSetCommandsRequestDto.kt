package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BotSetCommandsRequestDto(
    @JsonProperty("commands")val commands: List<BotCommandDto>,
)
