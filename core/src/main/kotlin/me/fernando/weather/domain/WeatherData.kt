package me.fernando.weather.domain

data class WeatherData(
    val location: Location? = null,
    val forecasts: List<Forecast>? = null,
)
