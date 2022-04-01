package me.fernando.weather.domain

import org.junit.jupiter.api.Test

class ForecastTest {
    @Test
    fun creationForecast() {
        val forecast = Forecast(
            timeDataForecasted = 1648846800L,
            temperature = 7.04F,
            temperatureMin = 4.83F,
            temperatureMax = 9.04F,
            weather =  "Clouds",
            description = "few clouds",
            icon = "02n",
        )
        assert(forecast.timeDataForecasted == 1648846800L)
        assert(forecast.temperature == 7.04F)
        assert(forecast.temperatureMin == 4.83F)
        assert(forecast.temperatureMax == 9.04F)
        assert(forecast.weather == "Clouds")
        assert(forecast.description == "few clouds")
        assert(forecast.icon == "02n")
    }
}
