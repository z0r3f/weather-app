package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.github.z0r3f.weather.core.telegram.cqrs.ConfigureAllAvailableCommandsMessage
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType
import io.github.z0r3f.weather.core.telegram.port.TelegramRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ConfigureAllAvailableCommandsHandlerTest {

    private val telegramRepository: TelegramRepository = mock()

    private lateinit var sut: ConfigureAllAvailableCommandsHandler

    @BeforeEach
    fun setUp() {
        sut = ConfigureAllAvailableCommandsHandler(telegramRepository)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(telegramRepository)
    }

    @Test
    fun should_configure_all_commands_when_some_commands_are_not_configured() {
        whenever(telegramRepository.getAllTheCommands()).thenReturn(emptySet())

        sut.handle(ConfigureAllAvailableCommandsMessage())

        verify(telegramRepository).getAllTheCommands()
        verify(telegramRepository).setAllTheCommands(ACTIVE_COMMANDS)
    }

    @Test
    fun should_not_configure_any_commands_when_all_the_commands_are_configured_correctly() {
        whenever(telegramRepository.getAllTheCommands()).thenReturn(ACTIVE_COMMANDS)

        sut.handle(ConfigureAllAvailableCommandsMessage())

        verify(telegramRepository).getAllTheCommands()
    }

    private companion object {
        val ACTIVE_COMMANDS = BotMessageType.getAvailableBotMessages()
    }
}
