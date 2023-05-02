package io.github.z0r3f.weather.core.forecast.port

import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinate

interface DirectGeocodingRepository {
    fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinate>
}
