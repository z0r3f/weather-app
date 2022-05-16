package me.fernando.weather.api

import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherData

interface GetForecastRepository {
    fun getForecast(location: Location): WeatherData
}
