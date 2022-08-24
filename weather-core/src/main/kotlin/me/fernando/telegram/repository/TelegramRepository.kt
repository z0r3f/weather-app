package me.fernando.telegram.repository

interface TelegramRepository {
    fun sendMessage(chatId: Long, message: String)
}
