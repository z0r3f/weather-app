package io.github.z0r3f.weather.core.forecast.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByCityNameMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage
import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.github.z0r3f.weather.core.forecast.domain.AirDataMother
import io.github.z0r3f.weather.core.forecast.domain.LocationMother
import io.github.z0r3f.weather.core.forecast.event.RequestAirEvent
import io.github.z0r3f.weather.core.forecast.service.AirOverviewService
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class AirEventListenerTest {

    private lateinit var bus: ActionBus
    private lateinit var airOverviewService: AirOverviewService
    private lateinit var newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>

    private lateinit var sut: AirEventListener

    @BeforeEach
    internal fun setUp() {
        bus = mock()
        airOverviewService = mock()
        newMessageEventPublisher = mock()

        sut = AirEventListener(bus, airOverviewService, newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(bus, airOverviewService, newMessageEventPublisher)
    }

    @Test
    fun `onNewMessage should dispatch message when the event takes city name`() {
        val event = RequestAirEvent(CHAT, CITY_NAME)
        whenever(bus.dispatch(GetAirByCityNameMessage(CITY_NAME))).thenReturn(AIR_QUALITY_DATA)
        whenever(airOverviewService.generateOverviewMessage(AIR_QUALITY_DATA)).thenReturn(RESPONSE_CITY_NAME_FORMATTED)

        sut.onRequestAirEvent(event)

        verify(bus).dispatch(GetAirByCityNameMessage(CITY_NAME))
        verify(airOverviewService).generateOverviewMessage(AIR_QUALITY_DATA)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_CITY_NAME_FORMATTED,
            )
        )
    }

    @Test
    fun `onNewMessage should dispatch message with favourite locations if event does not take city name`() {
        val event = RequestAirEvent(CHAT, null)
        whenever(bus.dispatch(GetFavoriteLocationsMessage(CHAT))).thenReturn(listOf(FAVORITE_TOKYO))
        whenever(bus.dispatch(GetAirByFavoriteLocationMessage(FAVORITE_TOKYO))).thenReturn(AIR_QUALITY_DATA_TOKYO)
        whenever(airOverviewService.generateOverviewMessage(AIR_QUALITY_DATA_TOKYO)).thenReturn(RESPONSE_TOKYO_FORMATTED)

        sut.onRequestAirEvent(event)

        verify(bus).dispatch(GetFavoriteLocationsMessage(CHAT))
        verify(bus).dispatch(GetAirByFavoriteLocationMessage(FAVORITE_TOKYO))
        verify(airOverviewService).generateOverviewMessage(AIR_QUALITY_DATA_TOKYO)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_TOKYO_FORMATTED,
            )
        )

    }

    private companion object {
        val CHAT = mock<Chat>()
        const val CITY_NAME = "City name"
        const val RESPONSE_CITY_NAME_FORMATTED = "☃️ Very cold right now in City name"

        val AIR_QUALITY_DATA: AirData = AirDataMother().of(location = LocationMother().of(name = CITY_NAME))

        val FAVORITE_TOKYO = FavoriteLocation(chat = CHAT, name = "Tokyo", latitude = -35.6895, longitude = 139.6917, country = "Japan")
        val AIR_QUALITY_DATA_TOKYO: AirData = AirDataMother().of(location = FAVORITE_TOKYO.toLocation())
        const val RESPONSE_TOKYO_FORMATTED = "Poor air quality in Tokyo"
    }
}
