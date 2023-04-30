package me.fernando.weather.cqrs

import io.archimedesfw.cqrs.QueryMessage
import me.fernando.weather.domain.WeatherData

data class GetForecastByCityNameMessage(val cityName: String) : QueryMessage<WeatherData>
