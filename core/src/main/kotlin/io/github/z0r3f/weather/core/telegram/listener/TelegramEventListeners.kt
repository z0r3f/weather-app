package io.github.z0r3f.weather.core.telegram.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.event.NewAlertEvent
import io.github.z0r3f.weather.core.chat.event.NewLocationEvent
import io.github.z0r3f.weather.core.chat.event.RemoveAlertEvent
import io.github.z0r3f.weather.core.forecast.service.AddAlertOverviewService
import io.github.z0r3f.weather.core.forecast.service.AddFavoriteOverviewService
import io.github.z0r3f.weather.core.forecast.service.RemoveAlertOverviewService
import io.github.z0r3f.weather.core.telegram.cqrs.SendMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class TelegramEventListeners(
    private val addAlertOverviewService: AddAlertOverviewService,
    private val removeAlertOverviewService: RemoveAlertOverviewService,
    private val addFavoriteOverviewService: AddFavoriteOverviewService,
    private val bus: ActionBus
) {
    @EventListener
    fun onNewAlertEvent(event: NewAlertEvent) {
        val responseForNewAlert = addAlertOverviewService.generateOverviewMessage(event.hourOfDay)
        onNewMessage(MessageEvent(event.chat, responseForNewAlert))
    }

    @EventListener
    fun onRemoveAlertEvent(event: RemoveAlertEvent) {
        val responseForRemoveAlert = removeAlertOverviewService.generateOverviewMessage(event.hourOfDay)
        onNewMessage(MessageEvent(event.chat, responseForRemoveAlert))
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
