package me.fernando.weather.domain

import org.junit.jupiter.api.Test

internal class ForecastTest {
    @Test
    fun creationForecast() {
        val forecast = Forecast(
            timeDataForecasted = 1648846800L,
            temperature = 7.04,
            temperatureMin = 4.83,
            temperatureMax = 9.04,
            weather =  listOf(
                Weather(
                    id = 800,
                    main = "Clear",
                    description = "clear sky",
                    icon = "01d"
                )
            )
        )
        assert(forecast.timeDataForecasted == 1648846800L)
        assert(forecast.temperature == 7.04)
        assert(forecast.temperatureMin == 4.83)
        assert(forecast.temperatureMax == 9.04)
        assert(forecast.weather.size == 1)
        assert(forecast.weather[0].id == 800)
        assert(forecast.weather[0].main == "Clear")
    }
}
