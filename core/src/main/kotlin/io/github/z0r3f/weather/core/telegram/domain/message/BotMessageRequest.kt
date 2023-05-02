package io.github.z0r3f.weather.core.telegram.domain.message

data class BotMessageRequest(
    val command: BotMessageType,
    val arguments: String
)
