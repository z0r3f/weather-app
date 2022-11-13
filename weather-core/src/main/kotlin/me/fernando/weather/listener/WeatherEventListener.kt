package me.fernando.weather.listener

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType
import me.fernando.telegram.usecase.SendMessageCmd
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.event.RequestForecastFromCityNameEvent
import me.fernando.weather.event.RequestForecastFromFavoriteLocationsEvent
import me.fernando.weather.service.ForecastOverviewService
import me.fernando.weather.usecase.GetForecastByCityNameQry
import me.fernando.weather.usecase.GetForecastByFavoriteLocationQry
import me.fernando.weather.usecase.GetForecastByFavoriteLocationsQry

@Singleton
class WeatherEventListener(
    private val bus: UseCaseBus,
    private val forecastOverviewService: ForecastOverviewService = ServiceLocator.locate(),
) {

    @EventListener
    fun onNewRequestForecast(event: RequestForecastFromCityNameEvent) {
        val weatherData = bus(GetForecastByCityNameQry(event.cityName))
        bus(
            SendMessageCmd(
                event.chat,
                forecastOverviewService.generateOverviewMessage(weatherData),
                listOf(
                    BotCallback(
                        type = BotCallbackType.ADD,
                        data = "${BotCallbackType.ADD.name}:${event.cityName}"
                    )
                )
            )
        )
    }

    // TODO Simplify this ↓
    @EventListener
    fun onNewRequestForecast(event: RequestForecastFromFavoriteLocationsEvent) {
        val favoriteLocations = bus(GetForecastByFavoriteLocationsQry(event.chat))
        favoriteLocations.forEach { favoriteLocation ->
            val weatherData = restoreTheOriginalCityName(
                bus(GetForecastByFavoriteLocationQry(favoriteLocation)),
                favoriteLocation.name!!
            )
            bus(
                SendMessageCmd(
                    event.chat,
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
