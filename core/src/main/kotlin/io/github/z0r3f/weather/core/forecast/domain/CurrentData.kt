package io.github.z0r3f.weather.core.forecast.domain

data class CurrentData(
    val location: Location? = null,
    val current: Current? = null,
)
