package io.github.z0r3f.weather.core.forecast.domain

data class WeatherData(
    val location: Location? = null,
    val forecasts: List<Forecast>? = null,
)
