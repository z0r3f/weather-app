package me.fernando.weather.api.rest

import jakarta.inject.Singleton
import me.fernando.weather.api.client.DirectGeocodingRestClient
import me.fernando.weather.api.mapper.GeographicalCoordinateMapper
import me.fernando.weather.domain.GeographicalCoordinate
import me.fernando.weather.repository.DirectGeocodingRepository

@Singleton
open class DirectGeocodingRestRepository(
    private val directGeocodingRestClient: DirectGeocodingRestClient,
    private val geographicalCoordinateMapper: GeographicalCoordinateMapper
): DirectGeocodingRepository {

    override fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinate> {
        val coordinates = directGeocodingRestClient.getCoordinatesByLocationName(address)
        return coordinates.map { geographicalCoordinateMapper.toEntity(it) }
    }
}
