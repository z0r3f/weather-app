package me.fernando.telegram.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.telegram.domain.BotCommandType
import me.fernando.telegram.port.TelegramRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

internal class CheckIfTheCommandsAreConfiguredQryTest {

    private val telegramRepository = Mockito.mock(TelegramRepository::class.java)

    @AfterEach
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(telegramRepository)
    }

    @Test
    fun should_return_true_when_all_commands_are_configured() {
        `when`(telegramRepository.getAllTheCommands()).thenReturn(ACTIVE_COMMANDS)

        val areTheSame = CheckIfTheCommandsAreConfiguredQry(telegramRepository).fakeRun()

        assertTrue(areTheSame)
        verify(telegramRepository).getAllTheCommands()
    }

    @Test
    fun should_return_false_when_some_commands_are_not_configured() {
        `when`(telegramRepository.getAllTheCommands()).thenReturn(emptySet())

        val areTheSame = CheckIfTheCommandsAreConfiguredQry(telegramRepository).fakeRun()

        assertFalse(areTheSame)
        verify(telegramRepository).getAllTheCommands()
    }

    private companion object {
        val ACTIVE_COMMANDS = BotCommandType.getAvailableCommands()
    }
}
