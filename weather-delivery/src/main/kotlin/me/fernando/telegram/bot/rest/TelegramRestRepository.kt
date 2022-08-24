package me.fernando.telegram.bot.rest

import jakarta.inject.Singleton
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.repository.TelegramRepository

@Singleton
class TelegramRestRepository(
    private val telegramApiClient: TelegramApiClient
): TelegramRepository {
    override fun sendMessage(chatId: Long, message: String) {
        telegramApiClient.sendMessage(chatId, message)
    }
}
