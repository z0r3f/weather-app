package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.weather.event.RequestForecastFromCityNameEvent
import me.fernando.weather.event.RequestForecastFromFavoriteLocationsEvent

// TODO should be a query?
class ForecastCmd(
    private val chat: Chat,
    private val cityName: String? = null,
    private val newRequestForecastFromCityNameEvent: ApplicationEventPublisher<RequestForecastFromCityNameEvent> = locate(),
    private val newRequestForecastFromFavoriteLocationsEvent: ApplicationEventPublisher<RequestForecastFromFavoriteLocationsEvent> = locate(),
) : Command<Unit>() {
    override fun run() {

        // TODO could it be an only event with optional city name??
        if (!cityName.isNullOrEmpty()) {
            newRequestForecastFromCityNameEvent.publishEvent(
                RequestForecastFromCityNameEvent(chat, cityName)
            )
        } else {
            newRequestForecastFromFavoriteLocationsEvent.publishEvent(
                RequestForecastFromFavoriteLocationsEvent(chat)
            )
        }
    }
}
