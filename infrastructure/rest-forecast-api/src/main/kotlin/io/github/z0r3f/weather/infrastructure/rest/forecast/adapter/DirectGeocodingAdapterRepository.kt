package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinate
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository
import io.github.z0r3f.weather.infrastructure.rest.forecast.client.DirectGeocodingRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.GeographicalCoordinateMapper
import jakarta.inject.Singleton

@Singleton
open class DirectGeocodingAdapterRepository(
    private val directGeocodingRestClient: DirectGeocodingRestClient,
    private val geographicalCoordinateMapper: GeographicalCoordinateMapper
): DirectGeocodingRepository {

    override fun getCoordinatesByLocationName(address: String): List<GeographicalCoordinate> {
        val coordinates = directGeocodingRestClient.getCoordinatesByLocationName(address)
        return coordinates.map { geographicalCoordinateMapper.toModel(it) }
    }
}
