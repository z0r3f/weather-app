package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.telegram.cqrs.CallbackUnknownMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class CallbackUnknownHandlerTest {

    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: ActionHandler<CallbackUnknownMessage, Unit>

    @BeforeEach
    internal fun setUp() {
        sut = CallbackUnknownHandler(newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(newMessageEventPublisher)
    }

    @Test
    internal fun `test handle`() {
        val chat = mock<Chat>()
        val action = CallbackUnknownMessage(
            chat = chat
        )

        sut.handle(action)

        verify(newMessageEventPublisher).publishEventAsync(MessageEvent(chat, ErrorMessageFactory.callbackUnknown()))
    }
}
