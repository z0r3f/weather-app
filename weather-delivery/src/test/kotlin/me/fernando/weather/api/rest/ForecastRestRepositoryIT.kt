package me.fernando.weather.api.rest

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import me.fernando.weather.api.client.ForecastRestClient
import me.fernando.weather.api.mapper.WeatherDataMapper
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest(propertySources = ["application-test.yml"])
internal class ForecastRestRepositoryIT {

    @Inject
    lateinit var forecastRestClient: ForecastRestClient

    @Inject
    lateinit var weatherDataMapper: WeatherDataMapper

    @Test
    fun `should return a list of forecasts`() {
        val repository = ForecastRestRepository(
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