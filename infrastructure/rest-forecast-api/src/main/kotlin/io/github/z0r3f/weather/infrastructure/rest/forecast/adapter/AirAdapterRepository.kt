package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.github.z0r3f.weather.infrastructure.rest.forecast.client.AirRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.AirDataMapper
import jakarta.inject.Singleton

@Singleton
open class AirAdapterRepository(
    private val airRestClient: AirRestClient,
    private val airDataMapper: AirDataMapper,
) {
    fun getAirQuality(latitude: Double, longitude: Double): AirData {
        val response = airRestClient.getAir(latitude, longitude)
        return airDataMapper.toModel(response)
    }
}
