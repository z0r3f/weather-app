package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class CoordinateDtoMother {
    open fun of(
        latitude: Double? = null,
        longitude: Double? = null,
    ) = CoordinateDto(
        latitude = latitude ?: 40.4167,
        longitude = longitude ?: -3.7033,
    )
}
