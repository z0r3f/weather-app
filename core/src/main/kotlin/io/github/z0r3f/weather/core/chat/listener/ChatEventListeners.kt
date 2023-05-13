package io.github.z0r3f.weather.core.chat.listener

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.chat.cqrs.AddFavoriteLocationMessage
import io.github.z0r3f.weather.core.chat.event.NewFavoriteEvent

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
