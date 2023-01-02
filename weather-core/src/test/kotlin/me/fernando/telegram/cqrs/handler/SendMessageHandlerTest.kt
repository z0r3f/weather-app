package me.fernando.telegram.cqrs.handler

import me.fernando.chat.domain.Chat
import me.fernando.telegram.cqrs.SendMessage
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType
import me.fernando.telegram.port.TelegramRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

internal class SendMessageHandlerTest {

    private val telegramRepository: TelegramRepository = mock()

    private lateinit var sut: SendMessageHandler

    @BeforeEach
    fun setUp() {
        sut = SendMessageHandler(telegramRepository)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(telegramRepository)
    }

    @Test
    fun throw_exception_when_the_message_requested_is_empty() {
        val exceptionThrown = Assertions.assertThrows(IllegalArgumentException::class.java) {
            sut.handle(SendMessage(CHAT, MESSAGE_EMPTY))
        }

        assertTrue(exceptionThrown.message == "The message is empty. Nothing to send")
        verifyNoInteractions(telegramRepository)
    }

    @Test
    fun sends_the_message_when_there_are_no_errors() {
        sut.handle(SendMessage(CHAT, MESSAGE))

        verify(telegramRepository).sendMessage(CHAT_ID, MESSAGE_SENT)
    }

    @Test
    fun sends_the_message_with_callbacks_when_there_are_no_errors_and_there_are_some_callback() {
        sut.handle(SendMessage(CHAT, MESSAGE, CALLBACKS))

        verify(telegramRepository).sendMessage(CHAT_ID, MESSAGE_SENT, CALLBACKS)
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat( id = CHAT_ID)
        const val MESSAGE = "Hello World. What happens?"
        const val MESSAGE_SENT = "Hello World\\. What happens?"
        const val MESSAGE_EMPTY = ""
        val CALLBACKS = listOf(BotCallback(type = BotCallbackType.UNKNOWN, data = "Unknown"))
    }
}
