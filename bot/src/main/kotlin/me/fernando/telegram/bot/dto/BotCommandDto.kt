package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BotCommandDto(
    @JsonProperty("command") val command: String,
    @JsonProperty("description") val description: String,
)
