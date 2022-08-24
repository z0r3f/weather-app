package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BotGetCommandsResponseDto(
    @JsonProperty("ok") val ok: Boolean,
    @JsonProperty("result") val result: List<BotCommandDto>,
) {
    fun isSuccessful() = ok
}
