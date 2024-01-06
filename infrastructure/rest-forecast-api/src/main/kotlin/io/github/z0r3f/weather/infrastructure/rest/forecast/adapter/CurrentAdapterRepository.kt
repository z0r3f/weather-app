package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.core.forecast.port.CurrentRepository
import io.github.z0r3f.weather.infrastructure.rest.forecast.client.CurrentRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.CurrentDataMapper
import jakarta.inject.Singleton

@Singleton
open class CurrentAdapterRepository(
    private val currentRestClient: CurrentRestClient,
    private val currentDataMapper: CurrentDataMapper,
): CurrentRepository {
    override fun getCurrent(latitude: Double, longitude: Double): CurrentData {
        val response = currentRestClient.getCurrent(latitude, longitude)
        return currentDataMapper.toModel(response)
    }
}
