package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import io.github.z0r3f.weather.core.forecast.port.ForecastRepository

@Singleton
class GetForecastByFavoriteLocationHandler(
    private val forecastRepository: ForecastRepository
) : ActionHandler<io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByFavoriteLocationMessage, WeatherData> {
    override fun handle(action: io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByFavoriteLocationMessage): WeatherData {
        return forecastRepository.getForecast(action.favoriteLocation.latitude!!, action.favoriteLocation.longitude!!)
    }
}
