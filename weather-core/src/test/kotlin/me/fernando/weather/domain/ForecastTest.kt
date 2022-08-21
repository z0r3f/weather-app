package me.fernando.weather.domain

import org.junit.jupiter.api.Test
import java.time.Instant

internal class ForecastTest {
    @Test
    fun creationForecast() {
        val forecast = Forecast(
            timeDataForecasted = Instant.parse("2022-02-15T18:35:24.00Z"),
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
        assert(forecast.timeDataForecasted == Instant.parse("2022-02-15T18:35:24.00Z"))
        assert(forecast.temperature == 7.04)
        assert(forecast.temperatureMin == 4.83)
        assert(forecast.temperatureMax == 9.04)
        assert(forecast.weather?.size  == 1)
        assert(forecast.weather?.get(0)?.id == 800)
        assert(forecast.weather?.get(0)?.main == "Clear")
    }
}
