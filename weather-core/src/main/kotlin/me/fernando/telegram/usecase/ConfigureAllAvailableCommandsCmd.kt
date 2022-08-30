package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.telegram.domain.message.BotMessageType
import me.fernando.telegram.port.TelegramRepository
import org.slf4j.LoggerFactory

class ConfigureAllAvailableCommandsCmd(
    private val telegramRepository: TelegramRepository = locate(),
) : Command<Unit>() {
    override fun run() {
        val configuredCommands = telegramRepository.getAllTheCommands()
        val availableCommands = BotMessageType.getAvailableCommands()

        if (configuredCommands == availableCommands) {
            LOG.info("All the commands are configured correctly")
        } else {
            LOG.info("Some commands are not configured. Configuring them...")
            telegramRepository.setAllTheCommands(BotMessageType.getAvailableCommands())
            LOG.info("All the commands are now configured correctly")
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ConfigureAllAvailableCommandsCmd::class.java)
    }
}
