package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import me.fernando.telegram.cqrs.ConfigureAllAvailableCommandsMessage
import me.fernando.telegram.domain.message.BotMessageType
import me.fernando.telegram.port.TelegramRepository
import org.slf4j.LoggerFactory

@Singleton
class ConfigureAllAvailableCommandsHandler(
    private val telegramRepository: TelegramRepository,
) : ActionHandler<ConfigureAllAvailableCommandsMessage, Unit> {
    override fun handle(action: ConfigureAllAvailableCommandsMessage) {
        val configuredCommands = telegramRepository.getAllTheCommands()
        val availableCommands = BotMessageType.getAvailableBotMessages()

        if (configuredCommands == availableCommands) {
            LOG.info("All the commands are configured correctly")
        } else {
            LOG.info("Some commands are not configured. Configuring them...")
            telegramRepository.setAllTheCommands(availableCommands)
            LOG.info("All the commands are now configured correctly")
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ConfigureAllAvailableCommandsHandler::class.java)
    }
}
