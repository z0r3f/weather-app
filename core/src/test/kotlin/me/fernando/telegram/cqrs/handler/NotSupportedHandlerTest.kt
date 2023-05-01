package me.fernando.telegram.cqrs.handler

import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.telegram.cqrs.NotSupportedQueryMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class NotSupportedHandlerTest {

    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: NotSupportedHandler

    @BeforeEach
    internal fun setUp() {
        sut = NotSupportedHandler(newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(newMessageEventPublisher)
    }

    @Test
    internal fun `handle should publish a MessageEvent with "Command not supported" text`() {
        val chat = mock<Chat>()

        sut.handle(NotSupportedQueryMessage(chat))

        verify(newMessageEventPublisher).publishEventAsync(MessageEvent(chat, ErrorMessageFactory.commandNotSupported()))
    }
}
