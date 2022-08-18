package me.fernando.weather.repository

import me.fernando.weather.domain.WeatherData

interface GetForecastRepository {
    fun getForecast(latitude: Double, longitude: Double): WeatherData
}
