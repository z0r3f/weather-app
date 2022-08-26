package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.usecase.Query
import me.fernando.telegram.domain.BotCommandType
import me.fernando.telegram.port.TelegramRepository
import org.slf4j.LoggerFactory

class CheckIfTheCommandsAreConfiguredQry(
    private val telegramRepository: TelegramRepository = ServiceLocator.locate(),
) : Query<Boolean>() {

    override fun run(): Boolean {
        val configuredCommands = telegramRepository.getAllTheCommands()
        val availableCommands = BotCommandType.getAvailableCommands()

        return if (configuredCommands == availableCommands) {
            LOG.info("All the commands are configured")
            true
        } else {
            LOG.info("Some commands are not configured")
            false
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(CheckIfTheCommandsAreConfiguredQry::class.java)
    }
}
