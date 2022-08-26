package me.fernando.weather.port

import me.fernando.weather.domain.GeographicalCoordinate

interface DirectGeocodingRepository {
    fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinate>
}
