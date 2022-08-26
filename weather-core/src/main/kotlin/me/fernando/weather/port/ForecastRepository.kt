package me.fernando.weather.port

import me.fernando.weather.domain.WeatherData

interface ForecastRepository {
    fun getForecast(latitude: Double, longitude: Double): WeatherData
}
