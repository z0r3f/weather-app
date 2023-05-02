package me.fernando.telegram.bot.adapter

import jakarta.inject.Singleton
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.BotCommandDto
import me.fernando.telegram.bot.dto.BotSetCommandsRequestDto
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessage
import io.github.z0r3f.weather.core.telegram.port.TelegramRepository

@Singleton
class TelegramAdapterRepository(
    private val telegramApiClient: TelegramApiClient,
) : TelegramRepository {
    override fun sendMessage(chatId: Long, message: String, callbacks: List<BotCallback>?) {
        val replyMarkup = callbacks?.let { callbackList -> "{\"inline_keyboard\":[${callbackList.map { it.toJson() }}]}" }

        telegramApiClient.sendMessage(chatId, message, replyMarkup)
    }

    override fun getAllTheCommands(): Set<BotMessage> {
        val botCommands = telegramApiClient.getBotCommands()
        return if (botCommands.isSuccessful()) {
            botCommands.result.map { botCommand ->
                BotMessage(botCommand.command, botCommand.description)
            }.toSet()
        } else {
            emptySet()
        }
    }

    override fun setAllTheCommands(commands: Set<BotMessage>) {
        telegramApiClient.setBotCommands(
            BotSetCommandsRequestDto(
                commands.map { botCommand ->
                    BotCommandDto(botCommand.command, botCommand.description)
                }
            )
        )
    }
}
