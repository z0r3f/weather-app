package me.fernando.weather.domain

import java.time.Instant

open class ForecastMother {
    open fun of(
        timeDataForecasted: Instant? = Instant.parse("2022-02-15T21:00:00.00Z"),
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
