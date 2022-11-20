package me.fernando.telegram.bot.config

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.context.annotation.Requires
import io.micronaut.context.annotation.Value
import io.micronaut.context.env.Environment
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerShutdownEvent
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.cqrs.ConfigureAllAvailableCommandsMessage
import org.slf4j.LoggerFactory

@Singleton
@Requires(notEnv = [Environment.TEST])
class ConfigBot(
    private val bus: ActionBus,
    private val telegramApiClient: TelegramApiClient,
    @Value("\${telegram.webhook}") private val telegramWebhook: String,
) {
    @EventListener
    fun onStartup(event: ServerStartupEvent) {
        LOG.info("ConfigBot started")
        telegramApiClient.setWebhook(telegramWebhook)
        bus.dispatch(ConfigureAllAvailableCommandsMessage())
    }

    @EventListener
    fun onShutdown(event: ServerShutdownEvent) {
        LOG.info("ConfigBot stopped")
        telegramApiClient.deleteWebhook()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ConfigBot::class.java)
    }
}
