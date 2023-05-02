package me.fernando.weather.api.adapter

import jakarta.inject.Singleton
import me.fernando.weather.api.client.DirectGeocodingRestClient
import me.fernando.weather.api.mapper.GeographicalCoordinateMapper
import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinate
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository

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
