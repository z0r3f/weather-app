package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.infrastructure.rest.forecast.client.CurrentRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.CurrentDataMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class CurrentAdapterRepositoryTest {

    @Inject
    lateinit var currentRestClient: CurrentRestClient
    @Inject
    lateinit var currentDataMapper: CurrentDataMapper

    @Test
    internal fun `should return the current weather by coordinate of Ciudad Real`() {
        val sut = CurrentAdapterRepository(
            currentRestClient,
            currentDataMapper,
        )

        val result = sut.getCurrent(
            latitude = 38.9851,
            longitude = -3.9284,
        )

        assertEquals("Ciudad Real", result.location?.name)
        assertEquals("ES", result.location?.country)

        with(result.current ?: fail("Current weather data is null")) {
            assertNotNull(temperature)
            assertNotNull(feelsLike)
            assertNotNull(temperatureMin)
            assertNotNull(temperatureMax)
            assertNotNull(pressure)
            assertNotNull(humidity)
            assertNotNull(weather)
            assertNotNull(windSpeed)
            assertNotNull(windDirection)
            assertNotNull(visibility)
            assertNotNull(cloudiness)
        }
    }
}
