package me.fernando.telegram.bot.config

import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.runtime.server.event.ServerShutdownEvent
import io.micronaut.runtime.server.event.ServerStartupEvent
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.usecase.ConfigureAllAvailableCommandsCmd
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class ConfigBotTest {
    private val telegramApiClient = mock<TelegramApiClient>()
    private val bus = mock<UseCaseBus>()

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
        verify(bus).invoke(ConfigureAllAvailableCommandsCmd())
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
