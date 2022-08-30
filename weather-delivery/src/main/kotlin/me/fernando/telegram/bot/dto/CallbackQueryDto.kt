package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CallbackQueryDto(
    @JsonProperty("id") val id: String,
    @JsonProperty("from") val from: UserDto,
    @JsonProperty("message") val message: MessageDto?,
    @JsonProperty("chat_instance") val chatInstance: String,
    @JsonProperty("data") val data: String?,
)
