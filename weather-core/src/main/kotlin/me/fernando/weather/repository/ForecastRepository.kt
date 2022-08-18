package me.fernando.weather.repository

import me.fernando.weather.domain.WeatherData

interface ForecastRepository {
    fun getForecast(latitude: Double, longitude: Double): WeatherData
}
