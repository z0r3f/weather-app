package me.fernando.telegram.port

import me.fernando.telegram.domain.BotCommand
import me.fernando.telegram.domain.callback.BotCallback

interface TelegramRepository {
    fun sendMessage(chatId: Long, message: String, callbacks: List<BotCallback>? = null)

    fun getAllTheCommands(): Set<BotCommand>

    fun setAllTheCommands(commands: Set<BotCommand>)
}
