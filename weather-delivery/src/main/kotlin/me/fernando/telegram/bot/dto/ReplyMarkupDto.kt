package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class ReplyMarkupDto(
    @JsonProperty("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButtonDto>>,
)
