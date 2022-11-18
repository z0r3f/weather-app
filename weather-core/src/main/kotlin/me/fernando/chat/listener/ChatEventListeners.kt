package me.fernando.chat.listener

import io.archimedesfw.cqrs.ActionBus
import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.event.NewFavoriteEvent
import me.fernando.chat.event.RequestHelpDataEvent
import me.fernando.chat.usecase.AddFavoriteLocationCmd
import me.fernando.telegram.cqrs.SendMessage
import me.fernando.util.generateOverviewMessage
import me.fernando.weather.service.HelpOverviewService

// TODO Change the name, repeat with `me.fernando.telegram.event.ChatEventListeners`
@Singleton
class ChatEventListeners(
    private val deprecatedBus: UseCaseBus,
    private val bus: ActionBus,
    private val helpOverviewService: HelpOverviewService,
) {
    @EventListener
    fun onNewAlertEvent(event: NewFavoriteEvent) {
        deprecatedBus(
            AddFavoriteLocationCmd(
                chat = event.chat,
                favoriteLocation = event.location.toFavoriteLocation()
            )
        )
    }

    @EventListener
    fun onRequestHelpDataEvent(event: RequestHelpDataEvent) {
        val response = helpOverviewService.generateOverviewMessage()
        bus.dispatch(SendMessage(event.chat, response))
    }
}
