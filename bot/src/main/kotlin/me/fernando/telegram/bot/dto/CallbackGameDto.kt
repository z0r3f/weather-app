package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CallbackGameDto(
    @JsonProperty("game_short_name") val gameShortName: String
)
