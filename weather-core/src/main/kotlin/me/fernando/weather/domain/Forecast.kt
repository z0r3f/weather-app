package me.fernando.weather.domain

import java.beans.Visibility

data class Forecast(
    val timeDataForecasted: Long,
    val temperature: Float,
    val feelsLike: Float? = null,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val pressure: Float? = null,
    val humidity: Float? = null,
    val weather: String,
    val description: String,
    val icon: String,
    val windSpeed: Float? = null,
    val visibility: Long? = null,
)
