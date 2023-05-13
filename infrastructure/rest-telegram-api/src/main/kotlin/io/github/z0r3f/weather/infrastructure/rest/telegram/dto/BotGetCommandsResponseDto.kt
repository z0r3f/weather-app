package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BotGetCommandsResponseDto(
    @JsonProperty("ok") val ok: Boolean,
    @JsonProperty("result") val result: List<BotCommandDto>,
) {
    fun isSuccessful() = ok
}
