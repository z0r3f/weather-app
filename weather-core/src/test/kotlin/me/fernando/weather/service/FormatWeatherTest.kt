package me.fernando.weather.service

import me.fernando.weather.domain.ForecastMother
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.domain.WeatherMother
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.Instant

internal class FormatWeatherTest {

    @Test
    fun format() {
        val actual = FormatWeather.overview(WEATHER_DATA)

        assertAll(
            { assert(actual.lines().count() == 8) },
            { assert(actual.lines().first() == "*Madrid*") },
            { assert(actual.contains("15\\-02\\-2022")) },
            { assert(actual.contains("16\\-02\\-2022")) },
            { assert(!actual.contains("17\\-02\\-2022")) },
        )
    }

    companion object {
        val WEATHER_DATA = WeatherDataMother().of(
            forecasts =
            listOf(
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-15T09:00:00.00Z"),
                    temperature = 12.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-15T15:00:00.00Z"),
                    temperature = 23.0,
                    weather = listOf(WeatherMother().of(icon = "02d"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-15T21:00:00.00Z"),
                    temperature = 18.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-16T09:00:00.00Z"),
                    temperature = 13.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-16T15:00:00.00Z"),
                    temperature = 24.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-16T21:00:00.00Z"),
                    temperature = 17.0,
                    weather = listOf(WeatherMother().of(icon = "02d"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-17T15:00:00.00Z"),
                    temperature = 24.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-17T21:00:00.00Z"),
                    temperature = 17.0,
                    weather = listOf(WeatherMother().of(icon = "02d"))
                ),
            ),
        )
    }
}
