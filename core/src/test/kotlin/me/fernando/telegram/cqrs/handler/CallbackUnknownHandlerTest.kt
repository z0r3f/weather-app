package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.telegram.cqrs.CallbackUnknownMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory
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
