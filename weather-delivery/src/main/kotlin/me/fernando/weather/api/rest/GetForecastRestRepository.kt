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

    override fun getForecast(location: Location): WeatherData {
        val response = getForecastRestClient.getForecast(location.latitude, location.longitude)
        return weatherDataMapper.toEntity(response)
    }
}
