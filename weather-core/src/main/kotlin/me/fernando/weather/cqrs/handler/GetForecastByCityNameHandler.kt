package me.fernando.weather.cqrs.handler

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.cqrs.ActionHandler
import me.fernando.weather.cqrs.GetForecastByCityNameQueryMessage
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.port.DirectGeocodingRepository
import me.fernando.weather.port.ForecastRepository

class GetForecastByCityNameHandler(
    private val directGeocodingRepository: DirectGeocodingRepository = ServiceLocator.locate(),
    private val forecastRepository: ForecastRepository = ServiceLocator.locate(),
) : ActionHandler<GetForecastByCityNameQueryMessage, WeatherData> {

    override fun handle(message: GetForecastByCityNameQueryMessage): WeatherData {
        if (message.cityName.isEmpty()) {
            throw IllegalArgumentException("Invalid location")
        }
        val location = directGeocodingRepository.getCoordinatesByLocationName(message.cityName).first()

        val weatherData = forecastRepository.getForecast(location.latitude, location.longitude)
        return weatherData.copy(location = weatherData.location?.copy(name = message.cityName))
    }
}
