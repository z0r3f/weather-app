package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateDto(
    @JsonProperty("update_id") val updateId: Long,
    @JsonProperty("message") val message: MessageDto?,
    @JsonProperty("edited_message") val editedMessage: MessageDto?,
    @JsonProperty("channel_post") val channelPost: MessageDto?,
    @JsonProperty("edited_channel_post") val editedChannelPost: MessageDto?,
    @JsonProperty("callback_query") val callbackQuery: CallbackQueryDto?,
) {
    fun hasCallbackQuery(): Boolean {
        return callbackQuery != null
    }
}
