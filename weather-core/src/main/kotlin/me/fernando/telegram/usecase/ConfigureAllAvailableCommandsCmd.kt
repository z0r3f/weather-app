package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.usecase.Command
import me.fernando.telegram.domain.BotCommandType
import me.fernando.telegram.port.TelegramRepository

class ConfigureAllAvailableCommandsCmd(
    private val telegramRepository: TelegramRepository = ServiceLocator.locate(),
) : Command<Unit>() {
    override fun run() = telegramRepository.setAllTheCommands(BotCommandType.getAvailableCommands())
}
