package me.fernando.telegram.port

import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.message.BotMessage

interface TelegramRepository {
    fun sendMessage(chatId: Long, message: String, callbacks: List<BotCallback>? = null)

    fun getAllTheCommands(): Set<BotMessage>

    fun setAllTheCommands(commands: Set<BotMessage>)
}
