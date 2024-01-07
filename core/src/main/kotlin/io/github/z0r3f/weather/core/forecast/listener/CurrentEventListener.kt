package io.github.z0r3f.weather.core.forecast.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByCityNameMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage
import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.core.forecast.event.RequestCurrentEvent
import io.github.z0r3f.weather.core.forecast.service.CurrentOverviewService
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class CurrentEventListener(
    private val bus: ActionBus,
    private val currentOverviewService: CurrentOverviewService,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) {

    @EventListener
    fun onRequestCurrentEvent(event: RequestCurrentEvent) {
        if (event.cityName.isNullOrBlank()) {
            newCurrentRequestOnChat(event.chat)
        } else {
            newCurrentRequestWithCityName(event.chat, event.cityName)
        }
    }

    private fun newCurrentRequestOnChat(chat: Chat) {
        val favoriteLocations = bus.dispatch(GetFavoriteLocationsMessage(chat))
        favoriteLocations.forEach { favoriteLocation ->
            val currentData =
                restoreTheOriginalCityName(
                    bus.dispatch(GetCurrentByFavoriteLocationMessage(favoriteLocation)),
                    favoriteLocation.name!!
                )

            newMessageEventPublisher.publishEventAsync(
                MessageEvent(
                    chat,
                    currentOverviewService.generateOverviewMessage(currentData),
                )
            )
        }
    }

    private fun restoreTheOriginalCityName(currentData: CurrentData, cityName: String) =
        currentData.copy(location = currentData.location?.copy(name = cityName))

    private fun newCurrentRequestWithCityName(chat: Chat, cityName: String) {
        val currentData = bus.dispatch(GetCurrentByCityNameMessage(cityName))

        newMessageEventPublisher.publishEventAsync(
            MessageEvent(
                chat,
                currentOverviewService.generateOverviewMessage(currentData),
            )
        )
    }
}
