package me.fernando.telegram.listener

import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.event.NewAlertEvent
import me.fernando.chat.event.NewLocationEvent
import me.fernando.telegram.event.MessageEvent
import me.fernando.telegram.usecase.SendMessageCmd
import me.fernando.weather.service.AddAlertOverviewService
import me.fernando.weather.service.AddFavoriteOverviewService

// TODO Change the name, repeat with `me.fernando.chat.listener.ChatEventListeners`
@Singleton
class TelegramEventListeners(
    private val bus: UseCaseBus,
    private val addAlertOverviewService: AddAlertOverviewService,
    private val addFavoriteOverviewService: AddFavoriteOverviewService,
) {
    @EventListener
    fun onNewAlertEvent(event: NewAlertEvent) {
        val responseForNewAlert = addAlertOverviewService.generateOverviewMessage(event.hourOfDay)
        sendMessage(MessageEvent(event.chat, responseForNewAlert))
    }

    @EventListener
    fun onNewLocation(event: NewLocationEvent) {
        val responseForNewLocationEvent = addFavoriteOverviewService.generateOverviewMessage(event.location)
        sendMessage(MessageEvent(event.chat, responseForNewLocationEvent))
    }

    private fun sendMessage(messageEvent: MessageEvent) {
        bus(SendMessageCmd(messageEvent.chat, messageEvent.message))
    }
}
