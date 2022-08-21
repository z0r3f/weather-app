package me.fernando.weather.domain

import java.time.Instant

data class Forecast(
    val timeDataForecasted: Instant? = null,
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
