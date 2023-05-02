package io.github.z0r3f.weather.core.forecast.domain

open class GeographicalCoordinateMother {

    open fun of(
        name: String? = "Lisbon",
        latitude: Double? = 38.7077507,
        longitude: Double? = -9.1365919,
        country: String? = "PT",
    ): GeographicalCoordinate {
        return GeographicalCoordinate(
            name = name!!,
            latitude = latitude!!,
            longitude = longitude!!,
            country = country!!,
        )
    }
}
