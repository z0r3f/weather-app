package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Query
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.port.DirectGeocodingRepository
import me.fernando.weather.port.ForecastRepository

class GetForecastByCityNameQry(
    private val cityName: String,
    private val directGeocodingRepository: DirectGeocodingRepository = locate(),
    private val forecastRepository: ForecastRepository = locate(),
): Query<WeatherData>() {

    override fun run(): WeatherData {
        if (cityName.isEmpty()) {
            throw IllegalArgumentException("Invalid location")
        }
        val location = directGeocodingRepository.getCoordinatesByLocationName(cityName).first()

        return restoreTheOriginalCityName(forecastRepository.getForecast(location.latitude, location.longitude))
    }

    private fun restoreTheOriginalCityName(weatherData: WeatherData): WeatherData {
        return weatherData.copy(location = weatherData.location?.copy(name = cityName))
    }
}
