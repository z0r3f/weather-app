package io.github.z0r3f.weather.core.forecast.domain

open class WeatherDataMother {
    open fun of(
        location: Location? = LocationMother().of(),
        forecasts: List<Forecast>? = listOf(ForecastMother().of()),
    ): WeatherData {
        return WeatherData(
            location = location,
            forecasts = forecasts,
        )
    }
}
