package io.github.z0r3f.weather.core.forecast.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByCityNameMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage
import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.github.z0r3f.weather.core.forecast.event.RequestAirEvent
import io.github.z0r3f.weather.core.forecast.service.AirOverviewService
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class AirEventListener(
    private val bus: ActionBus,
    private val airOverviewService: AirOverviewService,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) {

    @EventListener
    fun onRequestCurrentEvent(event: RequestAirEvent) {
        if (event.cityName.isNullOrBlank()) {
            newAirRequestOnChat(event.chat)
        } else {
            newAirRequestWithCityName(event.chat, event.cityName)
        }
    }

    private fun newAirRequestOnChat(chat: Chat) {
        val favoriteLocations = bus.dispatch(GetFavoriteLocationsMessage(chat))
        favoriteLocations.forEach { favoriteLocation ->
            val currentData = restoreTheOriginalCityName(
                bus.dispatch(GetAirByFavoriteLocationMessage(favoriteLocation)),
                favoriteLocation.name!!
            )

            newMessageEventPublisher.publishEventAsync(
                MessageEvent(
                    chat,
                    airOverviewService.generateOverviewMessage(currentData),
                )
            )
        }
    }

    private fun restoreTheOriginalCityName(currentData: AirData, cityName: String) =
        currentData.copy(location = currentData.location?.copy(name = cityName))

    private fun newAirRequestWithCityName(chat: Chat, cityName: String) {
        val currentData = bus.dispatch(GetAirByCityNameMessage(cityName))

        newMessageEventPublisher.publishEventAsync(
            MessageEvent(
                chat,
                airOverviewService.generateOverviewMessage(currentData),
            )
        )
    }
}
