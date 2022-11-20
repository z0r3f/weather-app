package me.fernando.weather.listener

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.cqrs.ActionBus
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.chat.domain.Chat
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType
import me.fernando.telegram.event.MessageEvent
import me.fernando.weather.cqrs.GetFavoriteLocationsMessage
import me.fernando.weather.cqrs.GetForecastByCityNameMessage
import me.fernando.weather.cqrs.GetForecastByFavoriteLocationMessage
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.event.RequestForecastEvent
import me.fernando.weather.service.ForecastOverviewService

@Singleton
class WeatherEventListener(
    private val bus: ActionBus,
    private val forecastOverviewService: ForecastOverviewService = locate(),
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = locate(),
) {

    @EventListener
    fun onRequestForecastEvent(event: RequestForecastEvent) {
        if (event.cityName.isNullOrBlank()) {
            newForecastRequestOnChat(event.chat)
        } else {
            newForecastRequestWithCityName(event.chat, event.cityName)
        }
    }

    private fun newForecastRequestWithCityName(chat: Chat, cityName: String) {
        val weatherData = bus.dispatch(GetForecastByCityNameMessage(cityName))
        val botCallback = buildCallback(chat, cityName)

        newMessageEventPublisher.publishEvent(
            MessageEvent(
                chat,
                forecastOverviewService.generateOverviewMessage(weatherData),
                listOf(botCallback)
            )
        )
    }

    private fun buildCallback(chat: Chat, cityName: String): BotCallback {
        val favoriteLocations = bus.dispatch(GetFavoriteLocationsMessage(chat))
        val cityIsFavorite = favoriteLocations.find { favoriteLocation -> cityName.equals(favoriteLocation.name, true) }
            ?.let { return@let true } ?: false

        val botCallback = if (cityIsFavorite) {
            BotCallback(
                type = BotCallbackType.DELETE,
                data = "${BotCallbackType.DELETE.name}:${cityName}"
            )
        } else {
            BotCallback(
                type = BotCallbackType.ADD,
                data = "${BotCallbackType.ADD.name}:${cityName}"
            )
        }
        return botCallback
    }

    // TODO Simplify this ↓ ¿?
    private fun newForecastRequestOnChat(chat: Chat) {
        val favoriteLocations = bus.dispatch(GetFavoriteLocationsMessage(chat))
        favoriteLocations.forEach { favoriteLocation ->
            val weatherData = restoreTheOriginalCityName(
                bus.dispatch(GetForecastByFavoriteLocationMessage(favoriteLocation)),
                favoriteLocation.name!!
            )
            newMessageEventPublisher.publishEvent(
                MessageEvent(
                    chat,
                    forecastOverviewService.generateOverviewMessage(weatherData),
                    listOf(
                        BotCallback(
                            type = BotCallbackType.DELETE,
                            data = "${BotCallbackType.DELETE.name}:${weatherData.location!!.name}"
                        )
                    )
                )
            )
        }
    }

    private fun restoreTheOriginalCityName(weatherData: WeatherData, cityName: String): WeatherData {
        return weatherData.copy(location = weatherData.location?.copy(name = cityName))
    }
}
