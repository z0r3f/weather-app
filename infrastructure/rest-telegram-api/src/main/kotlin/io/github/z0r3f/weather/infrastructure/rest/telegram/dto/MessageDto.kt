package io.github.z0r3f.weather.infrastructure.rest.telegram.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageDto(
    @JsonProperty("message_id") val messageId: Long,
    @JsonProperty("from") val from: UserDto,
    @JsonProperty("date") val date: Long,
    @JsonProperty("chat") val chat: ChatDto,
    @JsonProperty("text") val text: String?,
    @JsonProperty("reply_markup") val replyMarkup: ReplyMarkupDto?,
)
