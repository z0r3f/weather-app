package io.github.z0r3f.weather.infrastructure.rest.telegram.adapter

import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessage
import io.github.z0r3f.weather.core.telegram.port.TelegramRepository
import io.github.z0r3f.weather.infrastructure.rest.telegram.client.TelegramApiClient
import io.github.z0r3f.weather.infrastructure.rest.telegram.dto.BotCommandDto
import io.github.z0r3f.weather.infrastructure.rest.telegram.dto.BotSetCommandsRequestDto
import jakarta.inject.Singleton

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
