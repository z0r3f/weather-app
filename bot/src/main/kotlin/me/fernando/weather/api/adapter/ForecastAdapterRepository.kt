package me.fernando.weather.api.adapter

import jakarta.inject.Singleton
import me.fernando.weather.api.client.ForecastRestClient
import me.fernando.weather.api.mapper.WeatherDataMapper
import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import io.github.z0r3f.weather.core.forecast.port.ForecastRepository

@Singleton
open class ForecastAdapterRepository(
    private val forecastRestClient: ForecastRestClient,
    private val weatherDataMapper: WeatherDataMapper
): ForecastRepository {

    override fun getForecast(latitude: Double, longitude: Double): WeatherData {
        val response = forecastRestClient.getForecast(latitude, longitude)
        return weatherDataMapper.toModel(response)
    }
}
