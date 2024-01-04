package io.github.z0r3f.weather.core.util

object WindIcon {
    private val icons = mapOf(
        0.0..45.0 to "↗️",
        45.0..90.0 to "➡️",
        90.0..135.0 to "↘️",
        135.0..180.0 to "⬇️",
        180.0..225.0 to "↙️",
        22.0..270.0 to "⬅️",
        270.0..315.0 to "↖️",
        315.0..360.0 to "⬆️"
    )

    fun getIcon(degrees: Double?): String? {
        if (degrees == null) return null
        return icons.entries.find { (range, _) -> degrees in range }?.value
    }
}
