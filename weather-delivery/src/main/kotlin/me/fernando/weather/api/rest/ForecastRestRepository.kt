package me.fernando.weather.api.rest

import jakarta.inject.Singleton
import me.fernando.weather.api.client.ForecastRestClient
import me.fernando.weather.api.mapper.WeatherDataMapper
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.repository.ForecastRepository

@Singleton
open class ForecastRestRepository(
    private val forecastRestClient: ForecastRestClient,
    private val weatherDataMapper: WeatherDataMapper
): ForecastRepository {

    override fun getForecast(latitude: Double, longitude: Double): WeatherData {
        val response = forecastRestClient.getForecast(latitude, longitude)
        return weatherDataMapper.toEntity(response)
    }
}
