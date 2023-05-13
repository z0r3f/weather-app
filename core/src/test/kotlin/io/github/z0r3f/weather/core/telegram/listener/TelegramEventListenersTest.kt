package io.github.z0r3f.weather.core.telegram.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.event.NewAlertEvent
import io.github.z0r3f.weather.core.chat.event.NewLocationEvent
import io.github.z0r3f.weather.core.chat.event.RemoveAlertEvent
import io.github.z0r3f.weather.core.forecast.domain.Location
import io.github.z0r3f.weather.core.forecast.service.AddAlertOverviewService
import io.github.z0r3f.weather.core.forecast.service.AddFavoriteOverviewService
import io.github.z0r3f.weather.core.forecast.service.RemoveAlertOverviewService
import io.github.z0r3f.weather.core.telegram.cqrs.SendMessage
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallbackType
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class TelegramEventListenersTest {

    private val addAlertOverviewService: AddAlertOverviewService = mock()
    private val removeAlertOverviewService: RemoveAlertOverviewService = mock()
    private val addFavoriteOverviewService: AddFavoriteOverviewService = mock()
    private val bus: ActionBus = mock()

    private lateinit var sut: TelegramEventListeners

    @BeforeEach
    fun setUp() {
        sut = TelegramEventListeners(addAlertOverviewService, removeAlertOverviewService, addFavoriteOverviewService, bus)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(addAlertOverviewService, removeAlertOverviewService, addFavoriteOverviewService, bus)
    }

    @Test
    fun onNewAlertEvent() {
        val chat = mock<Chat>()
        val hourOfDay = 9
        val response = "At 9"
        whenever(addAlertOverviewService.generateOverviewMessage(hourOfDay)).thenReturn(response)

        sut.onNewAlertEvent(NewAlertEvent(chat, hourOfDay))

        verify(addAlertOverviewService).generateOverviewMessage(hourOfDay)
        verify(bus).dispatch(SendMessage(chat, response))
    }

    @Test
    fun onRemoveAlertEvent() {
        val chat = mock<Chat>()
        val hourOfDay = 9
        val response = "At 9"
        whenever(removeAlertOverviewService.generateOverviewMessage(hourOfDay)).thenReturn(response)

        sut.onRemoveAlertEvent(RemoveAlertEvent(chat, hourOfDay))

        verify(removeAlertOverviewService).generateOverviewMessage(hourOfDay)
        verify(bus).dispatch(SendMessage(chat, response))
    }

    @Test
    fun onNewLocation() {
        val chat = mock<Chat>()
        val cityName = "Mexico City"
        val location = Location(name = cityName, latitude = -19.4326, longitude = -99.1332, country = "Mexico")
        val response = "At $cityName"
        whenever(addFavoriteOverviewService.generateOverviewMessage(location)).thenReturn(response)

        sut.onNewLocation(NewLocationEvent(chat, location))

        verify(addFavoriteOverviewService).generateOverviewMessage(location)
        verify(bus).dispatch(SendMessage(chat, response))
    }

    @Test
    fun onNewMessage() {
        val chat = mock<Chat>()
        val message = "message"
        val botCallback = BotCallback(
            type = BotCallbackType.ADD,
            data = "data"
        )
        val messageEvent = MessageEvent(chat = chat, message = message, listOf(botCallback))

        sut.onNewMessage(messageEvent)

        verify(bus).dispatch(SendMessage(chat, message, listOf(botCallback)))
    }
}
