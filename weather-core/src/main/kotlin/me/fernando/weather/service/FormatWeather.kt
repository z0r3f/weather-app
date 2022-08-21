package me.fernando.weather.service

import me.fernando.weather.domain.Forecast
import me.fernando.weather.domain.WeatherData
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class FormatWeather {

    companion object {

        private val HOURS = listOf(9, 15, 21)
        private const val PATTERN_FORMAT = "EE dd-MM-yyyy"
        private val icons = mapOf(
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

        fun overview(weatherData: WeatherData): String {
            return sanitize("""
                *${weatherData.location?.name}*
                ${generateHours(filterListingByTheMostImportantHours(weatherData))}
            """)
        }

        private fun filterListingByTheMostImportantHours(weatherData: WeatherData): WeatherData {
            val weatherDataFiltered =
                weatherData.forecasts?.filter { it.timeDataForecasted?.atZone(ZoneOffset.UTC)!!.hour in HOURS }

            return weatherData.copy(
                forecasts = weatherDataFiltered
            )
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

        private fun sanitize(text: String): String {
            return text.replace("-", "\\-").trimIndent()
        }
    }
}
