package me.fernando.telegram.domain.message

data class BotMessageRequest(
    val command: BotMessageType,
    val arguments: String
)
