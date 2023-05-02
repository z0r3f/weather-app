package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.service.HelpOverviewService
import io.github.z0r3f.weather.core.telegram.cqrs.HelpQueryMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.generateOverviewMessage
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class HelpHandlerTest {

    private val helpOverviewService = mock<HelpOverviewService>()
    private val eventPublisher = mock<ApplicationEventPublisher<MessageEvent>>()

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
