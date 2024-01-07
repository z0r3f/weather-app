package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.AirMessage
import io.github.z0r3f.weather.core.forecast.event.RequestAirEvent
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class AirHandlerTest {

    private val requestAirEvent: ApplicationEventPublisher<RequestAirEvent> = mock()

    private lateinit var sut: AirHandler

    @BeforeEach
    internal fun setUp() {
        sut = AirHandler(requestAirEvent)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(requestAirEvent)
    }

    @Test
    internal fun `handle should publish RequestAirEvent with correct arguments`() {
        val chat = mock<Chat>()
        val cityName = "City"
        val action = AirMessage(chat, cityName)

        sut.handle(action)

        verify(requestAirEvent).publishEventAsync(RequestAirEvent(chat, cityName))
    }
}
