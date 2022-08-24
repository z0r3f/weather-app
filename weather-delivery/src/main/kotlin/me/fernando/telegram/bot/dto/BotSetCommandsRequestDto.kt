package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BotSetCommandsRequestDto(
    @JsonProperty("commands")val commands: List<BotCommandDto>,
)
