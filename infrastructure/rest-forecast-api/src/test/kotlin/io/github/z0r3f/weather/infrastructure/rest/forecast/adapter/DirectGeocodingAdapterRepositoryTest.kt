package io.github.z0r3f.weather.infrastructure.rest.forecast.adapter

import io.github.z0r3f.weather.infrastructure.rest.forecast.client.DirectGeocodingRestClient
import io.github.z0r3f.weather.infrastructure.rest.forecast.mapper.GeographicalCoordinateMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
internal class DirectGeocodingAdapterRepositoryTest {

    @Inject
    lateinit var directGeocodingRestClient: DirectGeocodingRestClient

    @Inject
    lateinit var geographicalCoordinateMapper: GeographicalCoordinateMapper

    @Test
    fun `should return a list of coordinates`() {
        val repository = DirectGeocodingAdapterRepository(
            directGeocodingRestClient,
            geographicalCoordinateMapper
        )
        val result = repository.getCoordinatesByLocationName("São Paulo")
        assertNotNull(result)

        val coordinate = result.firstOrNull()
        assertNotNull(coordinate)
        assertEquals(-23.5506507, coordinate?.latitude)
        assertEquals(-46.6333824, coordinate?.longitude)
    }
}
