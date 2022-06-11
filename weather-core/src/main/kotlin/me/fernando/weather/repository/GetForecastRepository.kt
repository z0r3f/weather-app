package me.fernando.weather.repository

import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherData

interface GetForecastRepository {
    fun getForecast(location: Location): WeatherData
}
