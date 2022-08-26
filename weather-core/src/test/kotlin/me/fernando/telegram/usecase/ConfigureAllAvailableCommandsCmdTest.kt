package me.fernando.telegram.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.telegram.domain.BotCommandType
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
    fun should_configure_all_commands() {
        ConfigureAllAvailableCommandsCmd(telegramRepository).fakeRun()

        verify(telegramRepository).setAllTheCommands(ACTIVE_COMMANDS)
    }

    private companion object {
        val ACTIVE_COMMANDS = BotCommandType.getAvailableCommands()
    }
}
