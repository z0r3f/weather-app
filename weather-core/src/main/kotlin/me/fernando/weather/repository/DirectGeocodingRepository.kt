package me.fernando.weather.repository

import me.fernando.weather.domain.GeographicalCoordinate

interface DirectGeocodingRepository {
    fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinate>
}
