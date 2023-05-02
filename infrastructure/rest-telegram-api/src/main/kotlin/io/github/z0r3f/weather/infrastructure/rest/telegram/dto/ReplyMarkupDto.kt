package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class ReplyMarkupDto(
    @JsonProperty("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButtonDto>>,
)
