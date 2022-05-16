package me.fernando.weather.domain

data class Forecast(
    val timeDataForecasted: Long,
    val temperature: Double,
    val feelsLike: Double? = null,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val pressure: Double? = null,
    val humidity: Int? = null,
    val weather: List<Weather>,
    val windSpeed: Double? = null,
    val visibility: Int? = null,
    val probabilityOfPrecipitation: Double? = null,
)
