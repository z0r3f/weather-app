package me.fernando.telegram.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.chat.domain.Chat
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType.UNKNOWN
import me.fernando.telegram.port.TelegramRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class SendMessageCmdTest {

    private val telegramRepository = mock(TelegramRepository::class.java)

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(telegramRepository)
    }

    @Test
    fun throw_exception_when_the_message_requested_is_empty() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            SendMessageCmd(CHAT, MESSAGE_EMPTY, null, telegramRepository).fakeRun()
        }

        assertTrue(exceptionThrown.message == "The message is empty. Nothing to send")
        verifyNoInteractions(telegramRepository)
    }

    @Test
    fun sends_the_message_when_there_are_no_errors() {
        SendMessageCmd(CHAT, MESSAGE, null, telegramRepository).fakeRun()

        verify(telegramRepository).sendMessage(CHAT_ID, MESSAGE_SENT)
    }

    @Test
    fun sends_the_message_with_callbacks_when_there_are_no_errors_and_there_are_some_callback() {
        SendMessageCmd(CHAT, MESSAGE, CALLBACKS, telegramRepository).fakeRun()

        verify(telegramRepository).sendMessage(CHAT_ID, MESSAGE_SENT, CALLBACKS)
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat( id = CHAT_ID)
        const val MESSAGE = "Hello World. What happens?"
        const val MESSAGE_SENT = "Hello World\\. What happens?"
        const val MESSAGE_EMPTY = ""
        val CALLBACKS = listOf(BotCallback(type = UNKNOWN, data = "Unknown"))
    }
}
