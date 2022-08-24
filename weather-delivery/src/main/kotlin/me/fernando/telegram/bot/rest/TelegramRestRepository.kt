package me.fernando.telegram.bot.rest

import jakarta.inject.Singleton
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.BotCommandDto
import me.fernando.telegram.bot.dto.BotSetCommandsRequestDto
import me.fernando.telegram.domain.BotCommand
import me.fernando.telegram.repository.TelegramRepository

@Singleton
class TelegramRestRepository(
    private val telegramApiClient: TelegramApiClient,
) : TelegramRepository {
    override fun sendMessage(chatId: Long, message: String) {
        telegramApiClient.sendMessage(chatId, message)
    }

    override fun getAllTheCommands(): Set<BotCommand> {
        return if (telegramApiClient.getBotCommands().isSuccessful()) {
            telegramApiClient.getBotCommands().result.map { botCommand ->
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
