package io.github.z0r3f.weather.core.forecast.domain

open class CurrentMother {
    open fun of(
        temperature: Double? = 23.05,
        feelsLike: Double? = 22.11,
        temperatureMin: Double? = 23.05,
        temperatureMax: Double? = 23.9,
        pressure: Double? = 1021.0,
        humidity: Int? = 42,
        weather: Weather? = WeatherMother().of(),
        windSpeed: Double? = 2.53,
        windDirection: Double? = 146.0,
        windGust: Double? = 4.06,
        visibility: Int? = 10000,
        cloudiness: Int? = 100,
    ): Current {
        return Current(
            temperature = temperature,
            feelsLike = feelsLike,
            temperatureMin = temperatureMin,
            temperatureMax = temperatureMax,
            pressure = pressure,
            humidity = humidity,
            weather = weather,
            windSpeed = windSpeed,
            windDirection = windDirection,
            windGust = windGust,
            visibility = visibility,
            cloudiness = cloudiness,
        )
    }
}
