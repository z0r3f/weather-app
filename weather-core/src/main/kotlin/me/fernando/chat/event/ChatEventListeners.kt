package me.fernando.chat.event

import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.usecase.AddFavoriteLocationCmd
import me.fernando.telegram.usecase.SendMessageCmd
import me.fernando.util.generateOverviewMessage
import me.fernando.weather.service.HelpOverviewService

// TODO Change the name, repeat with `me.fernando.telegram.event.ChatEventListeners`
@Singleton
class ChatEventListeners(
    private val bus: UseCaseBus,
    private val helpOverviewService: HelpOverviewService,
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

    @EventListener
    fun onRequestHelpDataEvent(event: RequestHelpDataEvent) {
        val response = helpOverviewService.generateOverviewMessage()
        bus(SendMessageCmd(event.chat, response))
    }
}
