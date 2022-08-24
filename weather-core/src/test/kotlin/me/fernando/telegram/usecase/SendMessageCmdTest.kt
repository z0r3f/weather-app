package me.fernando.telegram.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.telegram.repository.TelegramRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

internal class SendMessageCmdTest {

    private val telegramApiClient = mock(TelegramRepository::class.java)

    @AfterEach
    fun tearDown() {
        Mockito.verifyNoMoreInteractions(telegramApiClient)
    }

    @Test
    fun throw_exception_when_the_message_requested_is_empty() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            SendMessageCmd(CHAT_ID, MESSAGE_EMPTY, telegramApiClient).fakeRun()
        }

        assertTrue(exceptionThrown.message == "The message is empty. Nothing to send")
        verifyNoInteractions(telegramApiClient)
    }

    @Test
    fun sends_the_message_when_there_are_no_errors() {
        SendMessageCmd(CHAT_ID, MESSAGE, telegramApiClient).fakeRun()

        verify(telegramApiClient).sendMessage(CHAT_ID, MESSAGE_SENT)
    }

    private companion object {
        const val CHAT_ID = 123L
        const val MESSAGE = "Hello World. What happens?"
        const val MESSAGE_SENT = "Hello World\\. What happens?"
        const val MESSAGE_EMPTY = ""
    }
}
