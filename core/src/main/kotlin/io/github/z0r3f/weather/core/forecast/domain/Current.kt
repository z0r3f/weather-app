package io.github.z0r3f.weather.core.forecast.domain

data class Current(
    val temperature: Double? = null,
    val feelsLike: Double? = null,
    val temperatureMin: Double? = null,
    val temperatureMax: Double? = null,
    val pressure: Double? = null,
    val humidity: Int? = null,
    val weather: Weather? = null,
    val windSpeed: Double? = null,
    val windDirection: Double? = null,
    val visibility: Int? = null,
    val cloudiness: Int? = null, // Cloudiness, %
)
