package me.fernando.telegram.cqrs.handler

import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.telegram.cqrs.HelpQueryMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.generateOverviewMessage
import me.fernando.weather.service.HelpOverviewService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class HelpHandlerTest {

    private val helpOverviewService: HelpOverviewService = mock()
    private val eventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: HelpHandler

    @BeforeEach
    fun setUp() {
        sut = HelpHandler(helpOverviewService, eventPublisher)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(helpOverviewService, eventPublisher)
    }

    @Test
    fun run_success() {
        whenever(helpOverviewService.generateOverviewMessage()).thenReturn("Help message")

        sut.handle(HelpQueryMessage(CHAT))

        verify(helpOverviewService).generateOverviewMessage()
        verify(eventPublisher).publishEventAsync(MessageEvent(CHAT, "Help message"))
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat(id = CHAT_ID)
    }
}
