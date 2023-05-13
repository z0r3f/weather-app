package io.github.z0r3f.weather.core.forecast.domain

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class ForecastTest {
    @Test
    fun creationForecast() {
        val forecast = Forecast(
            timeDataForecasted = LocalDateTime.ofEpochSecond(1644958800, 0, ZoneOffset.UTC),
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
        assert(forecast.timeDataForecasted == LocalDateTime.ofEpochSecond(1644958800, 0, ZoneOffset.UTC))
        assert(forecast.temperature == 7.04)
        assert(forecast.temperatureMin == 4.83)
        assert(forecast.temperatureMax == 9.04)
        assert(forecast.weather?.size  == 1)
        assert(forecast.weather?.get(0)?.id == 800)
        assert(forecast.weather?.get(0)?.main == "Clear")
    }
}
