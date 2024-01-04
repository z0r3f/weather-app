package io.github.z0r3f.weather.core.forecast.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByCityNameMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage
import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.core.forecast.domain.CurrentDataMother
import io.github.z0r3f.weather.core.forecast.domain.LocationMother
import io.github.z0r3f.weather.core.forecast.event.RequestCurrentEvent
import io.github.z0r3f.weather.core.forecast.service.CurrentOverviewService
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class CurrentEventListenerTest {


    private lateinit var bus: ActionBus
    private lateinit var currentOverviewService: CurrentOverviewService
    private lateinit var newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>

    private lateinit var sut: CurrentEventListener

    @BeforeEach
    internal fun setUp() {
        bus = mock()
        currentOverviewService = mock()
        newMessageEventPublisher = mock()

        sut = CurrentEventListener(bus, currentOverviewService, newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(bus, currentOverviewService, newMessageEventPublisher)
    }

    @Test
    fun `onNewMessage should dispatch message when the event takes city name`() {
        val event = RequestCurrentEvent(CHAT, CITY_NAME)
        whenever(bus.dispatch(GetCurrentByCityNameMessage(CITY_NAME))).thenReturn(CURRENT_DATA)
        whenever(currentOverviewService.generateOverviewMessage(CURRENT_DATA)).thenReturn(RESPONSE_CITY_NAME_FORMATTED)

        sut.onRequestCurrentEvent(event)

        verify(bus).dispatch(GetCurrentByCityNameMessage(CITY_NAME))
        verify(currentOverviewService).generateOverviewMessage(CURRENT_DATA)
        verify(newMessageEventPublisher).publishEventAsync(
            MessageEvent(
                chat = CHAT,
                message = RESPONSE_CITY_NAME_FORMATTED,
            )
        )
    }

    @Test
    fun `onNewMessage should dispatch message with favourite locations if event does not take city name`() {
        val event = RequestCurrentEvent(CHAT, null)
        whenever(bus.dispatch(GetFavoriteLocationsMessage(CHAT))).thenReturn(listOf(FAVORITE_TOKYO))
        whenever(bus.dispatch(GetCurrentByFavoriteLocationMessage(FAVORITE_TOKYO))).thenReturn(CURRENT_DATA_TOKYO)
        whenever(currentOverviewService.generateOverviewMessage(CURRENT_DATA_TOKYO)).thenReturn(RESPONSE_TOKYO_FORMATTED)

        sut.onRequestCurrentEvent(event)

        verify(bus).dispatch(GetFavoriteLocationsMessage(CHAT))
        verify(bus).dispatch(GetCurrentByFavoriteLocationMessage(FAVORITE_TOKYO))
        verify(currentOverviewService).generateOverviewMessage(CURRENT_DATA_TOKYO)
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

        val CURRENT_DATA: CurrentData = CurrentDataMother().of(location = LocationMother().of(name = CITY_NAME))

        val FAVORITE_TOKYO = FavoriteLocation(chat = CHAT, name = "Tokyo", latitude = -35.6895, longitude = 139.6917, country = "Japan")
        val CURRENT_DATA_TOKYO: CurrentData = CurrentDataMother().of(location = FAVORITE_TOKYO.toLocation())
        const val RESPONSE_TOKYO_FORMATTED = "🌧 Heavy rain in Tokyo"
    }
}
