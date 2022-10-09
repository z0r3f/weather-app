package me.fernando.weather.cqrs

import io.archimedesfw.cqrs.QueryMessage
import me.fernando.weather.domain.WeatherData

data class GetForecastByCityNameQueryMessage(val cityName: String) : QueryMessage<WeatherData>
