package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.core.util.WeatherIcon.icons
import io.github.z0r3f.weather.core.util.WindIcon
import io.github.z0r3f.weather.core.util.trimLeadingSpaces
import jakarta.inject.Singleton
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Singleton
class CurrentOverviewService : OverviewService<CurrentData> {
    override fun generateOverviewMessage(data: CurrentData) = """
            *${data.location?.name}*
            ${generateWeather(data)}
            ${generateWind(data)}
            ${generateHumidity(data)} ${generatePressure(data)}
            ${generateSunrise(data)} ${generateSunset(data)}
            
        """.trimLeadingSpaces()

    private fun generateWeather(currentData: CurrentData) = """
        ${icons[currentData.current?.weather?.icon]} ${currentData.current?.temperature?.roundToInt() ?: "--"}°C (${currentData.current?.feelsLike?.roundToInt() ?: "--"}°C)
        """.trimLeadingSpaces()

    private fun generateWind(currentData: CurrentData) = """
        ${WindIcon.getIcon(currentData.current?.windDirection)} ${mpsToKph(currentData.current?.windSpeed)} km/h (${mpsToKph(currentData.current?.windGust)} km/h)
        """.trimLeadingSpaces()

    private fun mpsToKph(mps: Double?) = mps?.times(3.6)?.roundToInt() ?: "--"

    private fun generateHumidity(currentData: CurrentData) = "💧${currentData.current?.humidity ?: "--"}%"

    private fun generatePressure(currentData: CurrentData) = "🌀${currentData.current?.pressure ?: "--"} hPa"

    private fun generateSunrise(currentData: CurrentData): String {
        val zoneOffset = ZoneOffset.ofTotalSeconds(currentData.location?.timezone ?: 0)
        val sunriseTime = Instant.ofEpochSecond(currentData.location?.sunrise ?: 0).atZone(zoneOffset)
        return "🌅 ${sunriseTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
    }

    private fun generateSunset(currentData: CurrentData): String {
        val zoneOffset = ZoneOffset.ofTotalSeconds(currentData.location?.timezone ?: 0)
        val sunsetTime = Instant.ofEpochSecond(currentData.location?.sunset ?: 0).atZone(zoneOffset)
        return "🌇 ${sunsetTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
    }
}
