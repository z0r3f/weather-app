package me.fernando.weather.service

import jakarta.inject.Singleton
import me.fernando.util.minElementsBy
import me.fernando.util.trimLeadingSpaces
import me.fernando.weather.domain.Forecast
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.service.comparator.HourComparator
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Singleton
class ForecastOverviewService(
    private val hourComparator: HourComparator,
) : OverviewService<WeatherData> {

    override fun generateOverviewMessage(data: WeatherData) = """
            *${data.location?.name}*
            ${generateHours(filterListingByTheMostImportantHours(data))}
        """.trimLeadingSpaces()

    private fun filterListingByTheMostImportantHours(weatherData: WeatherData): WeatherData {
        val firstHours = weatherData.forecasts!!.minElementsBy(hourComparator.comparator(LocalTime.of(FIRST_HOUR, 0)))
        val secondHours = weatherData.forecasts.minElementsBy(hourComparator.comparator(LocalTime.of(SECOND_HOUR,0)))
        val thirdHours = weatherData.forecasts.minElementsBy(hourComparator.comparator(LocalTime.of(THIRD_HOUR,0)))

        return weatherData.copy(forecasts = firstHours + secondHours + thirdHours)
    }

    private fun generateHours(weatherData: WeatherData): String {
        val forecastByDays = forecastByDays(weatherData)
        val forecastByDaysWithThreeHours = filterDaysWithThreeHours(forecastByDays)

        var text = ""
        forecastByDaysWithThreeHours?.forEach {
            text += """
                *${it.key}*
                `${generateHoursByForecast(it.value)}`
                """
        }
        return text
    }

    private fun forecastByDays(weatherData: WeatherData): Map<String, List<Forecast>>? {
        val formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneOffset.UTC)
        return weatherData.forecasts?.groupBy { formatter.format(it.timeDataForecasted) }
    }

    private fun filterDaysWithThreeHours(forecastByDays: Map<String, List<Forecast>>?): Map<String, List<Forecast>>? {
        return forecastByDays?.filter { it.value.size == 3 }
    }

    private fun generateHoursByForecast(forecasts: List<Forecast>): String {
        return forecasts.joinToString(separator = "|") {
            "${icons[it.weather?.first()?.icon]} ${it.temperature?.roundToInt()}°C"
        }
    }

    private companion object {

        const val FIRST_HOUR = 8
        const val SECOND_HOUR = 15
        const val THIRD_HOUR = 22

        const val PATTERN_FORMAT = "EE dd-MM-yyyy"
        val icons = mapOf(
            "01d" to "☀️",
            "02d" to "🌤",
            "03d" to "🌥",
            "04d" to "☁️",
            "09d" to "🌦",
            "10d" to "🌧",
            "11d" to "🌩",
            "13d" to "☃️",
            "50d" to "🌫️",
            "01n" to "☀️",
            "02n" to "🌤",
            "03n" to "🌥",
            "04n" to "☁️",
            "09n" to "🌦",
            "10n" to "🌧",
            "11n" to "🌩",
            "13n" to "☃️",
            "50n" to "🌫️",
        )
    }
}
