package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(NON_NULL)
data class InlineKeyboardButtonDto(
    @JsonProperty("text") val text: String,
    @JsonProperty("url") val url: String? = null,
    @JsonProperty("callback_data") val callbackData: String? = null,
    @JsonProperty("switch_inline_query") val switchInlineQuery: String? = null,
    @JsonProperty("switch_inline_query_current_chat") val switchInlineQueryCurrentChat: String? = null,
    @JsonProperty("callback_game") val callbackGame: CallbackGameDto? = null,
    @JsonProperty("pay") val pay: Boolean? = null,
)
