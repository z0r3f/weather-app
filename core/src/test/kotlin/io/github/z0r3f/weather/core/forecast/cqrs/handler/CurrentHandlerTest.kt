package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.CurrentMessage
import io.github.z0r3f.weather.core.forecast.event.RequestCurrentEvent
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class CurrentHandlerTest {

    private val requestCurrentEvent: ApplicationEventPublisher<RequestCurrentEvent> = mock()

    private lateinit var sut: CurrentHandler

    @BeforeEach
    internal fun setUp() {
        sut = CurrentHandler(requestCurrentEvent)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(requestCurrentEvent)
    }

    @Test
    internal fun `handle should publish RequestCurrentEvent with correct arguments`() {
        val chat = mock<Chat>()
        val cityName = "City"
        val action = CurrentMessage(chat, cityName)

        sut.handle(action)

        verify(requestCurrentEvent).publishEventAsync(RequestCurrentEvent(chat, cityName))
    }
}
