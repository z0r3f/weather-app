package me.fernando.weather.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import me.fernando.weather.cqrs.GetForecastByFavoriteLocationMessage
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.port.ForecastRepository

@Singleton
class GetForecastByFavoriteLocationHandler(
    private val forecastRepository: ForecastRepository
) : ActionHandler<GetForecastByFavoriteLocationMessage, WeatherData> {
    override fun handle(action: GetForecastByFavoriteLocationMessage): WeatherData {
        return forecastRepository.getForecast(action.favoriteLocation.latitude!!, action.favoriteLocation.longitude!!)
    }
}
