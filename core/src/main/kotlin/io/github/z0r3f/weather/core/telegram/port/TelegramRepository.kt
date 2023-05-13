package io.github.z0r3f.weather.core.telegram.port

import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessage

interface TelegramRepository {
    fun sendMessage(chatId: Long, message: String, callbacks: List<BotCallback>? = null)

    fun getAllTheCommands(): Set<BotMessage>

    fun setAllTheCommands(commands: Set<BotMessage>)
}
