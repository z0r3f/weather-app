package me.fernando.weather.service

import me.fernando.weather.domain.ForecastMother
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.domain.WeatherMother
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.Instant
import java.time.ZoneId

internal class ForecastOverviewServiceTest {

    private val sut = ForecastOverviewService()

    @Test
    fun generateForecastOverviewMessage() {
        val actual = sut.generateOverviewMessage(WEATHER_DATA)

        assertAll(
            { assert(actual.contains("*Madrid*")) },
            { assert(actual.contains("15-02-2022")) },
            { assert(actual.contains("16-02-2022")) },
            { assert(!actual.contains("17-02-2022")) },
        )
    }

    companion object {
        val WEATHER_DATA = WeatherDataMother().of(
            forecasts =
            listOf(
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-15T07:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 12.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-15T13:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 23.0,
                    weather = listOf(WeatherMother().of(icon = "02d"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-15T20:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 18.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-16T07:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 13.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-16T13:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 24.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-16T20:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 17.0,
                    weather = listOf(WeatherMother().of(icon = "02d"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-17T13:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 24.0,
                    weather = listOf(WeatherMother().of(icon = "01n"))
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2022-02-17T20:00:00.00Z").atZone(
                        ZoneId.of("Europe/Madrid")
                    ).toLocalDateTime(),
                    temperature = 17.0,
                    weather = listOf(WeatherMother().of(icon = "02d"))
                ),
            ),
        )
    }
}
