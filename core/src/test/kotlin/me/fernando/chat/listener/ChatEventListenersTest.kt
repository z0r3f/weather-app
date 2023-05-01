package me.fernando.chat.listener

import io.archimedesfw.cqrs.ActionBus
import me.fernando.chat.cqrs.AddFavoriteLocationMessage
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.NewFavoriteEvent
import me.fernando.weather.domain.Location
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class ChatEventListenersTest {

    @Test
    fun `onNewAlertEvent should dispatch AddFavoriteLocationMessage with correct arguments`() {
        val bus = mock<ActionBus>()
        val chat = mock<Chat>()
        val cityName = "city"
        val event = NewFavoriteEvent(chat, Location(name = cityName, latitude = 1.0, longitude = 1.0, country = "Spain"))


        val sut = ChatEventListeners(bus)
        sut.onNewAlertEvent(event)

        verify(bus).dispatch(
            AddFavoriteLocationMessage(
                chat = chat,
                favoriteLocation = event.location.toFavoriteLocation()
            )
        )
    }

}
