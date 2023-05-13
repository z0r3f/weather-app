package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.infrastructure.rest.forecast.client.ForecastRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.WeatherDataMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
internal class ForecastAdapterRepositoryTest {

    @Inject
    lateinit var forecastRestClient: ForecastRestClient

    @Inject
    lateinit var weatherDataMapper: WeatherDataMapper

    @Test
    fun `should return a list of forecasts`() {
        val repository = ForecastAdapterRepository(
            forecastRestClient,
            weatherDataMapper
        )
        val result = repository.getForecast(
            latitude = 38.9851,
            longitude = -3.9284,
        )
        assertNotNull(result)
    }
}
