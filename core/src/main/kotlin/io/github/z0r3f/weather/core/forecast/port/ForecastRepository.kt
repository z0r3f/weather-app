package io.github.z0r3f.weather.core.forecast.port

import io.github.z0r3f.weather.core.forecast.domain.WeatherData

interface ForecastRepository {
    fun getForecast(latitude: Double, longitude: Double): WeatherData
}
