package me.fernando.telegram.domain

data class BotCommandRequest(
    val command: BotCommandType,
    val arguments: String
)
