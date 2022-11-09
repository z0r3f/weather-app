package me.fernando.telegram.usecase

import io.archimedesfw.usecase.fakeRun
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.RequestHelpDataEvent
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class HelpCmdTest {

    private val eventPublisher: ApplicationEventPublisher<RequestHelpDataEvent> = mock()

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(eventPublisher)
    }

    @Test
    fun run_success() {
        HelpCmd(CHAT, eventPublisher).fakeRun()

        verify(eventPublisher).publishEvent(RequestHelpDataEvent(CHAT))
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat(id = CHAT_ID)
    }
}
