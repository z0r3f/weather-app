package me.fernando.weather.domain

data class WeatherData(
    val location: Location,
    val forecasts: List<Forecast>
)
