package me.fernando.telegram.bot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageDto(
    @JsonProperty("message_id") val messageId: Long,
    @JsonProperty("from") val from: UserDto,
    @JsonProperty("date") val date: Long,
    @JsonProperty("chat") val chat: ChatDto,
    @JsonProperty("forward_from") val forwardFrom: UserDto?,
    @JsonProperty("forward_from_chat") val forwardFromChat: ChatDto?,
    @JsonProperty("forward_from_message_id") val forwardFromMessageId: Long?,
    @JsonProperty("forward_signature") val forwardSignature: String?,
    @JsonProperty("forward_sender_name") val forwardSenderName: String?,
    @JsonProperty("forward_date") val forwardDate: Long?,
    @JsonProperty("reply_to_message") val replyToMessage: MessageDto?,
    @JsonProperty("edit_date") val editDate: Long?,
    @JsonProperty("author_signature") val authorSignature: String?,
    @JsonProperty("text") val text: String?,
)
