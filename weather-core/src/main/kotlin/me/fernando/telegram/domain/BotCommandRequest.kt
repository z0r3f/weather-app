package me.fernando.telegram.domain

data class BotCommandRequest(
    val command: BotCommand,
    val arguments: String
)
