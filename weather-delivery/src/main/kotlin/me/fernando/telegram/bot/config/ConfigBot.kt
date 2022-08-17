package me.fernando.telegram.bot.config

import io.micronaut.context.annotation.Value
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerShutdownEvent
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import me.fernando.telegram.bot.client.TelegramApiClient

@Singleton
class ConfigBot(
    private val telegramApiClient: TelegramApiClient,
    @Value("\${telegram.webhook}") private val telegramWebhook: String,
) {
    @EventListener
    fun onStartup(event: ServerStartupEvent) {
        println("ConfigBot started")
        telegramApiClient.setWebhook(telegramWebhook)
    }

    @EventListener
    fun onShutdown(event: ServerShutdownEvent) {
        println("ConfigBot stopped")
        telegramApiClient.deleteWebhook()
    }
}
