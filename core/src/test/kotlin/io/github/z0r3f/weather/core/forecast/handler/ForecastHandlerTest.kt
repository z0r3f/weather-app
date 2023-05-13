package io.github.z0r3f.weather.core.forecast.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.ForecastMessage
import io.github.z0r3f.weather.core.forecast.cqrs.handler.ForecastHandler
import io.github.z0r3f.weather.core.forecast.event.RequestForecastEvent
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class ForecastHandlerTest {

    private val requestForecastEvent: ApplicationEventPublisher<RequestForecastEvent> = mock()

    private lateinit var sut: ForecastHandler

    @BeforeEach
    internal fun setUp() {
        sut = ForecastHandler(requestForecastEvent)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(requestForecastEvent)
    }

    @Test
    internal fun `handle should publish RequestForecastEvent with correct arguments`() {
        val chat = mock<Chat>()
        val cityName = "City"
        val hourOfDay = 11
        val action = ForecastMessage(chat, cityName, hourOfDay)

        sut.handle(action)

        verify(requestForecastEvent).publishEventAsync(RequestForecastEvent(chat, cityName, hourOfDay))
    }
}
