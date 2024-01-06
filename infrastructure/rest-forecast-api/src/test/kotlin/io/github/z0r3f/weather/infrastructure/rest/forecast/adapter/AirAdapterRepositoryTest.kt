package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.infrastructure.rest.forecast.client.AirRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.AirDataMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class AirAdapterRepositoryTest {

    @Inject
    lateinit var airRestClient: AirRestClient
    @Inject
    lateinit var airDataMapper: AirDataMapper

    @Test
    internal fun `should return the current air quality by coordinate of Ciudad Real`() {
        val sut = AirAdapterRepository(
            airRestClient,
            airDataMapper,
        )

        val result = sut.getAirQuality(
            latitude = CIUDAD_REAL_LATITUDE,
            longitude = CIUDAD_REAL_LONGITUDE,
        )

        assertEquals(CIUDAD_REAL_LATITUDE, result.location?.latitude)
        assertEquals(CIUDAD_REAL_LONGITUDE, result.location?.longitude)

        with(result.air ?: fail("Current air quality data is null")) {
            assertNotNull(carbonMonoxide, "carbonMonoxide should not be null")
            assertNotNull(nitrogenMonoxide, "nitrogenMonoxide should not be null")
            assertNotNull(nitrogenDioxide, "nitrogenDioxide should not be null")
            assertNotNull(ozone, "ozone should not be null")
            assertNotNull(sulphurDioxide, "sulphurDioxide should not be null")
            assertNotNull(fineParticlesMatter, "fineParticlesMatter should not be null")
            assertNotNull(coarseParticulateMatter, "coarseParticulateMatter should not be null")
            assertNotNull(ammonia, "ammonia should not be null")
        }

        with(result.air ?: fail("Current air quality data is null")) {
            assertTrue(airQualityIndex in 1..5, "airQualityIndex should be between 1 and 5")
            assertTrue(carbonMonoxide!! >= 0.0, "carbonMonoxide should be 0.0 or more")
            assertTrue(nitrogenMonoxide!! >= 0.0, "nitrogenMonoxide should be 0.0 or more")
            assertTrue(nitrogenDioxide!! >= 0.0, "nitrogenDioxide should be 0.0 or more")
            assertTrue(ozone!! >= 0.0, "ozone should be 0.0 or more")
            assertTrue(sulphurDioxide!! >= 0.0, "sulphurDioxide should be 0.0 or more")
            assertTrue(fineParticlesMatter!! >= 0.0, "fineParticlesMatter should be 0.0 or more")
            assertTrue(coarseParticulateMatter!! >= 0.0, "coarseParticulateMatter should be 0.0 or more")
            assertTrue(ammonia!! >= 0.0, "ammonia should be 0.0 or more")
        }
    }

    private companion object {
        private const val CIUDAD_REAL_LATITUDE = 38.9851
        private const val CIUDAD_REAL_LONGITUDE = -3.9284
    }
}
