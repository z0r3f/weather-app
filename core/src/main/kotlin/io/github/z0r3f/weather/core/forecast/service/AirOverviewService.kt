package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

@Singleton
class AirOverviewService(
    @Value("\${number.format}") private val numberFormat: String,
    @Value("\${number.locale}") private val locale: String
) : OverviewService<AirData> {
    override fun generateOverviewMessage(data: AirData): String {
        val labels = listOf("AQI", "CO", "NO", "NO2", "O3", "SO2", "PM2.5", "PM10", "NH3")
        val values = listOf(
            generateAQILine(data.air?.airQualityIndex ?: 0),
            "${formatNumber(data.air?.carbonMonoxide ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.nitrogenMonoxide ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.nitrogenDioxide ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.ozone ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.sulphurDioxide ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.fineParticlesMatter ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.coarseParticulateMatter ?: 0.0)} μg/m3",
            "${formatNumber(data.air?.ammonia ?: 0.0)} μg/m3"
        )

        val maxLabelSize = labels.maxOf { it.length } + 1
        val maxValueSize = values.maxOf { it.length }

        return """
            *${data.location?.name}*
            ```
            ${generateAlignedLine("AQI:", generateAQILine(data.air?.airQualityIndex ?: 0), maxLabelSize, maxValueSize)}
            ${generateAlignedLine("CO:", "${formatNumber(data.air?.carbonMonoxide ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("NO:", "${formatNumber(data.air?.nitrogenMonoxide ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("NO2:", "${formatNumber(data.air?.nitrogenDioxide ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("O3:", "${formatNumber(data.air?.ozone ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("SO2:", "${formatNumber(data.air?.sulphurDioxide ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("PM2.5:", "${formatNumber(data.air?.fineParticlesMatter ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("PM10:", "${formatNumber(data.air?.coarseParticulateMatter ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ${generateAlignedLine("NH3:", "${formatNumber(data.air?.ammonia ?: 0.0)} $UNIT", maxLabelSize, maxValueSize)}
            ```
        """.trimIndent()
    }

    private fun generateAQILine(aqi: Int): String {
        val filled = "█".repeat(aqi)
        val empty = "▒".repeat(5 - aqi)
        val emoji = when (aqi) {
            1 -> "😊"
            2 -> "🙂"
            3 -> "😐"
            4 -> "😕"
            else -> "😷"
        }
        return "$filled$empty $emoji $aqi/5"
    }

    private fun formatNumber(number: Double): String {
        val symbols = DecimalFormatSymbols(Locale.forLanguageTag(locale))
        val formatter = DecimalFormat(numberFormat, symbols)
        return formatter.format(number)
    }

    private fun generateAlignedLine(label: String, value: String, maxLabelSize: Int, maxValueSize: Int): String {
        val formattedLabel = String.format("%-${maxLabelSize}s", label)
        val formattedValue = String.format("%${maxValueSize}s", value)
        return "$formattedLabel $formattedValue"
    }

    private companion object {
        const val UNIT = "μg/m3"
    }
}
