package io.github.z0r3f.weather.core.forecast.domain

import java.time.LocalDateTime

data class Forecast(
    val timeDataForecasted: LocalDateTime? = null,
    val temperature: Double? = null,
    val feelsLike: Double? = null,
    val temperatureMin: Double? = null,
    val temperatureMax: Double? = null,
    val pressure: Double? = null,
    val humidity: Int? = null,
    val weather: List<Weather>? = null,
    val windSpeed: Double? = null,
    val visibility: Int? = null,
    val probabilityOfPrecipitation: Double? = null,
)
