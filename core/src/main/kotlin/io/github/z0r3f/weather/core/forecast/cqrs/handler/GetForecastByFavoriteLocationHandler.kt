package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import io.github.z0r3f.weather.core.forecast.port.ForecastRepository
import jakarta.inject.Singleton

@Singleton
class GetForecastByFavoriteLocationHandler(
    private val forecastRepository: ForecastRepository
) : ActionHandler<GetForecastByFavoriteLocationMessage, WeatherData> {
    override fun handle(action: GetForecastByFavoriteLocationMessage): WeatherData {
        return forecastRepository.getForecast(action.favoriteLocation.latitude!!, action.favoriteLocation.longitude!!)
    }
}
