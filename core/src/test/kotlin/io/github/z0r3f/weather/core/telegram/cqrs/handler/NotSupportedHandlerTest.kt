package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.telegram.cqrs.NotSupportedQueryMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory
import io.micronaut.context.event.ApplicationEventPublisher
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
    internal fun `handle should publish a MessageEvent with text Command not supported`() {
        val chat = mock<Chat>()

        sut.handle(NotSupportedQueryMessage(chat))

        verify(newMessageEventPublisher).publishEventAsync(MessageEvent(chat, ErrorMessageFactory.commandNotSupported()))
    }
}
