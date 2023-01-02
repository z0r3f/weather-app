package me.fernando.telegram.listener

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.event.NewAlertEvent
import me.fernando.chat.event.NewLocationEvent
import me.fernando.telegram.cqrs.SendMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.weather.service.AddAlertOverviewService
import me.fernando.weather.service.AddFavoriteOverviewService

@Singleton
class TelegramEventListeners(
    private val addAlertOverviewService: AddAlertOverviewService,
    private val addFavoriteOverviewService: AddFavoriteOverviewService,
    private val bus: ActionBus
) {
    @EventListener
    fun onNewAlertEvent(event: NewAlertEvent) {
        val responseForNewAlert = addAlertOverviewService.generateOverviewMessage(event.hourOfDay)
        onNewMessage(MessageEvent(event.chat, responseForNewAlert))
    }

    @EventListener
    fun onNewLocation(event: NewLocationEvent) {
        val responseForNewLocationEvent = addFavoriteOverviewService.generateOverviewMessage(event.location)
        onNewMessage(MessageEvent(event.chat, responseForNewLocationEvent))
    }

    @EventListener
    fun onNewMessage(messageEvent: MessageEvent) {
        bus.dispatch(SendMessage(messageEvent.chat, messageEvent.message, messageEvent.botCallbacks))
    }
}
