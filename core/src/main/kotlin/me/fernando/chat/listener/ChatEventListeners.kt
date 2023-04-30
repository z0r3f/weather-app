package me.fernando.chat.listener

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.cqrs.AddFavoriteLocationMessage
import me.fernando.chat.event.NewFavoriteEvent

@Singleton
class ChatEventListeners(
    private val bus: ActionBus,
) {
    @EventListener
    fun onNewAlertEvent(event: NewFavoriteEvent) {
        bus.dispatch(
            AddFavoriteLocationMessage(
                chat = event.chat,
                favoriteLocation = event.location.toFavoriteLocation()
            )
        )
    }
}
