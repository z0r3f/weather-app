package me.fernando.telegram.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.telegram.domain.message.BotMessageType
import me.fernando.telegram.port.TelegramRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class ConfigureAllAvailableCommandsCmdTest {

    private val telegramRepository = mock(TelegramRepository::class.java)

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(telegramRepository)
    }

    @Test
    fun should_configure_all_commands_when_some_commands_are_not_configured() {
        `when`(telegramRepository.getAllTheCommands()).thenReturn(emptySet())

        ConfigureAllAvailableCommandsCmd(telegramRepository).fakeRun()

        verify(telegramRepository).getAllTheCommands()
        verify(telegramRepository).setAllTheCommands(ACTIVE_COMMANDS)
    }

    @Test
    fun should_not_configure_any_commands_when_all_the_commands_are_configured_correctly() {
        `when`(telegramRepository.getAllTheCommands()).thenReturn(ACTIVE_COMMANDS)

        ConfigureAllAvailableCommandsCmd(telegramRepository).fakeRun()

        verify(telegramRepository).getAllTheCommands()
    }

    private companion object {
        val ACTIVE_COMMANDS = BotMessageType.getAvailableCommands()
    }
}
