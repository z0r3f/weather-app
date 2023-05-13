package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import io.github.z0r3f.weather.core.forecast.port.ForecastRepository
import io.github.z0r3f.weather.infrastructure.rest.forecast.client.ForecastRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.WeatherDataMapper
import jakarta.inject.Singleton

@Singleton
open class ForecastAdapterRepository(
    private val forecastRestClient: ForecastRestClient,
    private val weatherDataMapper: WeatherDataMapper,
): ForecastRepository {

    override fun getForecast(latitude: Double, longitude: Double): WeatherData {
        val response = forecastRestClient.getForecast(latitude, longitude)
        return weatherDataMapper.toModel(response)
    }
}
