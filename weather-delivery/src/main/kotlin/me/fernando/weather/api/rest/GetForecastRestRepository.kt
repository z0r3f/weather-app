package me.fernando.weather.api.rest

import jakarta.inject.Singleton
import me.fernando.weather.repository.GetForecastRepository
import me.fernando.weather.api.client.GetForecastRestClient
import me.fernando.weather.api.mapper.WeatherDataMapper
import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherData

@Singleton
open class GetForecastRestRepository(
    private val getForecastRestClient: GetForecastRestClient,
    private val weatherDataMapper: WeatherDataMapper
): GetForecastRepository {

    override fun getForecast(latitude: Double, longitude: Double): WeatherData {
        val response = getForecastRestClient.getForecast(latitude, longitude)
        return weatherDataMapper.toEntity(response)
    }
}
