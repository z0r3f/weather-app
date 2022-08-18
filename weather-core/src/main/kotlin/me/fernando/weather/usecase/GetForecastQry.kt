package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Query
import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.repository.ForecastRepository

class GetForecastQry(
    private val location: Location,
    private val forecastRepository: ForecastRepository = locate()
): Query<WeatherData>() {

    override fun run(): WeatherData {
        if (location.isInvalid()) {
            throw IllegalArgumentException("Invalid location")
        }
        return forecastRepository.getForecast(location.latitude!!, location.longitude!!)
    }
}
