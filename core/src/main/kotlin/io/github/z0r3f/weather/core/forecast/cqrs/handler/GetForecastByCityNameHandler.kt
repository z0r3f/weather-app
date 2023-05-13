package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByCityNameMessage
import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository
import io.github.z0r3f.weather.core.forecast.port.ForecastRepository

@Singleton
class GetForecastByCityNameHandler(
    private val directGeocodingRepository: DirectGeocodingRepository,
    private val forecastRepository: ForecastRepository,
) : ActionHandler<GetForecastByCityNameMessage, WeatherData> {

    override fun handle(action: GetForecastByCityNameMessage): WeatherData {
        if (action.cityName.isEmpty()) {
            throw IllegalArgumentException("Invalid location")
        }
        val location = directGeocodingRepository.getCoordinatesByLocationName(action.cityName).first()

        val weatherData = forecastRepository.getForecast(location.latitude, location.longitude)
        return weatherData.copy(location = weatherData.location?.copy(name = action.cityName))
    }
}
