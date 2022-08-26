package me.fernando.weather.api.adapter

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import me.fernando.weather.api.client.DirectGeocodingRestClient
import me.fernando.weather.api.mapper.GeographicalCoordinateMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest(propertySources = ["application-test.yml"])
internal class DirectGeocodingAdapterRepositoryIT {

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
