package io.github.z0r3f.weather.infrastructure.rest.telegram.config

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.telegram.cqrs.ConfigureAllAvailableCommandsMessage
import io.github.z0r3f.weather.infrastructure.rest.telegram.client.TelegramApiClient
import io.micronaut.context.annotation.Requires
import io.micronaut.context.annotation.Value
import io.micronaut.context.env.Environment
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerShutdownEvent
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@Requires(notEnv = [Environment.TEST])
class ConfigBot(
    private val bus: ActionBus,
    private val telegramApiClient: TelegramApiClient,
    @Value("\${telegram.webhook}") private val telegramWebhook: String,
) {
    @EventListener
    fun onStartup(@Suppress("UNUSED_PARAMETER") event: ServerStartupEvent) {
        LOG.info("ConfigBot started")
        telegramApiClient.setWebhook(telegramWebhook)
        bus.dispatch(ConfigureAllAvailableCommandsMessage())
    }

    @EventListener
    fun onShutdown(@Suppress("UNUSED_PARAMETER") event: ServerShutdownEvent) {
        LOG.info("ConfigBot stopped")
        telegramApiClient.deleteWebhook()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ConfigBot::class.java)
    }
}
