package me.fernando.telegram.port

import me.fernando.telegram.domain.BotCommand

interface TelegramRepository {
    fun sendMessage(chatId: Long, message: String)

    fun getAllTheCommands(): Set<BotCommand>

    fun setAllTheCommands(commands: Set<BotCommand>)
}
