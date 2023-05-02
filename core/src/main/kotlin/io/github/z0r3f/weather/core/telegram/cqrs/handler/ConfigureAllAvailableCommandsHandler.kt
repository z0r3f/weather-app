package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.telegram.cqrs.ConfigureAllAvailableCommandsMessage
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType
import io.github.z0r3f.weather.core.telegram.port.TelegramRepository
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
