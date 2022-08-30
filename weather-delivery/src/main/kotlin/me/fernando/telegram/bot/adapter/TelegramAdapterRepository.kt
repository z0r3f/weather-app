package me.fernando.telegram.bot.adapter

import jakarta.inject.Singleton
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.BotCommandDto
import me.fernando.telegram.bot.dto.BotSetCommandsRequestDto
import me.fernando.telegram.domain.BotCommand
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.port.TelegramRepository

@Singleton
class TelegramAdapterRepository(
    private val telegramApiClient: TelegramApiClient,
) : TelegramRepository {
    override fun sendMessage(chatId: Long, message: String, callbacks: List<BotCallback>?) {
        val replyMarkup = callbacks?.let { callbackList -> "{\"inline_keyboard\":[${callbackList.map { it.toJson() }}]}" }

        telegramApiClient.sendMessage(chatId, message, replyMarkup)
    }

    override fun getAllTheCommands(): Set<BotCommand> {
        val botCommands = telegramApiClient.getBotCommands()
        return if (botCommands.isSuccessful()) {
            botCommands.result.map { botCommand ->
                BotCommand(botCommand.command, botCommand.description)
            }.toSet()
        } else {
            emptySet()
        }
    }

    override fun setAllTheCommands(commands: Set<BotCommand>) {
        telegramApiClient.setBotCommands(
            BotSetCommandsRequestDto(
                commands.map { botCommand ->
                    BotCommandDto(botCommand.command, botCommand.description)
                }
            )
        )
    }
}
