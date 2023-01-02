package me.fernando.weather.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import me.fernando.weather.cqrs.GetForecastByCityNameMessage
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.port.DirectGeocodingRepository
import me.fernando.weather.port.ForecastRepository

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
