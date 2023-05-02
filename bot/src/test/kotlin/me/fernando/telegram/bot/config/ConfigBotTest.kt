package me.fernando.telegram.bot.config

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.runtime.server.event.ServerShutdownEvent
import io.micronaut.runtime.server.event.ServerStartupEvent
import me.fernando.telegram.bot.client.TelegramApiClient
import io.github.z0r3f.weather.core.telegram.cqrs.ConfigureAllAvailableCommandsMessage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class ConfigBotTest {
    private val telegramApiClient = mock<TelegramApiClient>()
    private val bus = mock<ActionBus>()

    private lateinit var sut: ConfigBot

    @BeforeEach
    fun setUpEach() {
        sut = ConfigBot(bus, telegramApiClient, MY_WEBHOOK_URL)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(bus)
        verifyNoMoreInteractions(telegramApiClient)
    }

    @Test
    fun onStartup() {
        sut.onStartup(STARTUP_EVENT)

        verify(telegramApiClient).setWebhook(MY_WEBHOOK_URL)
        verify(bus).dispatch(ConfigureAllAvailableCommandsMessage())
    }

    @Test
    fun onShutdown() {
        sut.onShutdown(SHUTDOWN_EVENT)

        verify(telegramApiClient).deleteWebhook()
    }

    private companion object {
        const val MY_WEBHOOK_URL = "https://webhook-url.me"
        val STARTUP_EVENT = mock<ServerStartupEvent>()
        val SHUTDOWN_EVENT = mock<ServerShutdownEvent>()
    }
}
