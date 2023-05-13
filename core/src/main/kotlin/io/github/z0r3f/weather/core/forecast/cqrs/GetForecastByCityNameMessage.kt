package io.github.z0r3f.weather.core.forecast.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.forecast.domain.WeatherData

data class GetForecastByCityNameMessage(val cityName: String) : QueryMessage<WeatherData>
