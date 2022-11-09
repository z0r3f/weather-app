package me.fernando.chat.event

import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.usecase.AddFavoriteLocationCmd

@Singleton
class ChatEventListeners(
    private val bus: UseCaseBus,
) {
    @EventListener
    fun onNewAlertEvent(event: NewFavoriteEvent) {
        bus(
            AddFavoriteLocationCmd(
                chat = event.chat,
                favoriteLocation = event.location.toFavoriteLocation()
            )
        )
    }
}
