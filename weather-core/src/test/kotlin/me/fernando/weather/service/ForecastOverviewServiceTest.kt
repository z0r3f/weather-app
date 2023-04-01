package me.fernando.weather.service

import me.fernando.weather.domain.ForecastMother
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.domain.WeatherMother
import me.fernando.weather.service.comparator.HourComparator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.Instant
import java.time.ZoneId

internal class ForecastOverviewServiceTest {

    private val sut = ForecastOverviewService(HourComparator())

    @Test
    fun generateForecastOverviewMessage() {
        val actual = sut.generateOverviewMessage(WEATHER_DATA)

        assertAll(
            { assert(actual.contains("*Madrid*")) },
            { assert(actual.contains("*dom 02-04-2023*")) },
            { assert(actual.contains("`🌤 2°C|🌤 14°C|🌤 7°C`")) },
            { assert(actual.contains("*lun 03-04-2023*")) },
            { assert(actual.contains("`🌤 2°C|🌤 15°C|🌤 9°C`")) },
            { assert(actual.contains("*mar 04-04-2023*")) },
            { assert(actual.contains("`🌤 3°C|🌤 16°C|🌤 7°C`")) },
			{ assert(actual.contains("*mié 05-04-2023*")) },
			{ assert(actual.contains("`🌤 1°C|🌤 16°C|🌤 8°C`")) },
        )
    }

    companion object {
        val WEATHER_DATA = WeatherDataMother().of(
            forecasts =
            listOf(
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-01T12:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 14.21,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-01T15:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 15.08,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-01T18:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 13.15,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-01T21:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 9.23,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T00:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 5.37,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T03:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 3.09,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T06:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 2.09,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T09:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 9.16,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T12:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 14.36,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T15:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 15.64,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T18:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 12.11,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-02T21:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 6.5,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T00:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 3.97,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T03:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 2.54,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T06:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 1.68,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T09:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 9.31,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T12:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 14.77,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T15:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 17.17,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T18:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 13.95,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-03T21:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 9.14,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T00:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 6.28,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T03:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 3.94,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T06:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 2.76,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T09:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 9.68,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T12:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 15.72,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T15:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 17.8,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T18:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 12.84,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-04T21:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 6.55,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T00:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 3.55,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T03:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 2.04,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T06:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 1.26,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T09:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 10.11,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T12:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 16.36,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T15:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 18.41,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T18:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 13.85,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-05T21:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 8.18,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-06T00:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 4.97,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-06T03:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 2.85,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-06T06:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 1.88,
                    weather = listOf(WeatherMother().of())
                ),
                ForecastMother().of(
                    timeDataForecasted = Instant.parse("2023-04-06T09:00:00.00Z").atZone(ZoneId.of("Europe/Madrid"))
                        .toLocalDateTime(),
                    temperature = 9.92,
                    weather = listOf(WeatherMother().of())
                ),
            ),
        )
    }
}
