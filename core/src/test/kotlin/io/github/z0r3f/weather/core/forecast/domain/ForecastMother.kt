package io.github.z0r3f.weather.core.forecast.domain

import java.time.LocalDateTime
import java.time.ZoneOffset

open class ForecastMother {
    open fun of(
        timeDataForecasted: LocalDateTime? = LocalDateTime.ofEpochSecond(1644958800, 0, ZoneOffset.UTC),
        temperature: Double? = 23.05,
        feelsLike: Double? = 22.51,
        temperatureMin: Double? = 23.05,
        temperatureMax: Double? = 23.9,
        pressure: Double? = 1021.0,
        humidity: Int? = 42,
        weather: List<Weather>? = listOf(WeatherMother().of()),
        windSpeed: Double? = 2.53,
        visibility: Int? = 10000,
        probabilityOfPrecipitation: Double? = 0.32,
    ): Forecast {
        return Forecast(
            timeDataForecasted = timeDataForecasted,
            temperature = temperature,
            feelsLike = feelsLike,
            temperatureMin = temperatureMin,
            temperatureMax = temperatureMax,
            pressure = pressure,
            humidity = humidity,
            weather = weather,
            windSpeed = windSpeed,
            visibility = visibility,
            probabilityOfPrecipitation = probabilityOfPrecipitation,
        )
    }
}
