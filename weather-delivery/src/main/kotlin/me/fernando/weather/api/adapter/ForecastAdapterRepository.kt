package me.fernando.weather.api.adapter

import jakarta.inject.Singleton
import me.fernando.weather.api.client.ForecastRestClient
import me.fernando.weather.api.mapper.WeatherDataMapper
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.port.ForecastRepository

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
