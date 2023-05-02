package io.github.z0r3f.weather.core.forecast.listener

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByCityNameMessage
import io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import io.github.z0r3f.weather.core.forecast.event.RequestForecastEvent
import io.github.z0r3f.weather.core.forecast.service.ForecastOverviewService
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallbackType
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.isNull
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

@Singleton
class WeatherEventListener(
    private val bus: ActionBus,
    private val forecastOverviewService: ForecastOverviewService,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) {

    @EventListener
    fun onRequestForecastEvent(event: RequestForecastEvent) {
        if (event.cityName.isNullOrBlank()) {
            newForecastRequestOnChat(event.chat, event.hourOfDay)
        } else {
            newForecastRequestWithCityName(event.chat, event.cityName)
        }
    }

    private fun newForecastRequestWithCityName(chat: Chat, cityName: String) {
        val weatherData = bus.dispatch(GetForecastByCityNameMessage(cityName))
        val botCallback = buildCallback(chat, cityName)

        newMessageEventPublisher.publishEventAsync(
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

    private fun newForecastRequestOnChat(chat: Chat, hourOfDay: Int?) {
        val favoriteLocations = bus.dispatch(GetFavoriteLocationsMessage(chat))
        favoriteLocations.forEach { favoriteLocation ->
            val weatherData = restoreTheOriginalCityName(
                bus.dispatch(GetForecastByFavoriteLocationMessage(favoriteLocation)),
                favoriteLocation.name!!
            )
            newMessageEventPublisher.publishEventAsync(
                MessageEvent(
                    chat,
                    forecastOverviewService.generateOverviewMessage(weatherData),
                    buildCallBackForRequestOnChat(weatherData.location!!.name!!, hourOfDay)
                )
            )
        }
    }

    private fun buildCallBackForRequestOnChat(name: String, hourOfDay: Int?): List<BotCallback> {
        val deleteFavoriteLocation = BotCallback(
            type = BotCallbackType.DELETE,
            data = "${BotCallbackType.DELETE.name}:$name"
        )
        return if (hourOfDay.isNull()) {
            listOf(deleteFavoriteLocation)
        } else {
            val deleteAlert = BotCallback(
                type = BotCallbackType.DELETE_ALERT,
                data = "${BotCallbackType.DELETE_ALERT.name}:$hourOfDay"
            )
            listOf(deleteFavoriteLocation, deleteAlert)
        }
    }

    private fun restoreTheOriginalCityName(weatherData: WeatherData, cityName: String): WeatherData {
        return weatherData.copy(location = weatherData.location?.copy(name = cityName))
    }
}
