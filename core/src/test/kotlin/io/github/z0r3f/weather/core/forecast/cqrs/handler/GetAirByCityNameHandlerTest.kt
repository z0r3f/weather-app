package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByCityNameMessage
import io.github.z0r3f.weather.core.forecast.domain.AirDataMother
import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinateMother
import io.github.z0r3f.weather.core.forecast.port.AirRepository
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class GetAirByCityNameHandlerTest {

    private val directGeocodingRepository: DirectGeocodingRepository = mock()
    private val airRepository: AirRepository = mock()

    private lateinit var sut : GetAirByCityNameHandler

    @BeforeEach
    internal fun setUp() {
        sut = GetAirByCityNameHandler(directGeocodingRepository, airRepository)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(directGeocodingRepository, airRepository)
    }

    @Test
    internal fun `should throw IllegalArgumentException when cityName is blank`() {
        val message = GetAirByCityNameMessage(" ")

        assertThrows(IllegalArgumentException::class.java) { sut.handle(message) }
    }

    @Test
    internal fun `should throw IllegalArgumentException when location not found`() {
        val message = GetAirByCityNameMessage("CityName")
        whenever(directGeocodingRepository.getCoordinatesByLocationName("CityName")).thenReturn(emptyList())

        assertThrows(IllegalArgumentException::class.java) { sut.handle(message) }
        verify(directGeocodingRepository).getCoordinatesByLocationName("CityName")
    }

    @Test
    internal fun `should return AirData when location found`() {
        val message = GetAirByCityNameMessage("CityName")
        val geographicalCoordinate = GeographicalCoordinateMother().of(name = "CityName")
        val currentData = AirDataMother().of(location = geographicalCoordinate.toLocation())
        whenever(directGeocodingRepository.getCoordinatesByLocationName("CityName")).thenReturn(listOf(geographicalCoordinate))
        whenever(airRepository.getAirQuality(geographicalCoordinate.latitude, geographicalCoordinate.longitude)).thenReturn(currentData)

        val result = sut.handle(message)

        assertEquals(currentData, result)
        verify(directGeocodingRepository).getCoordinatesByLocationName("CityName")
        verify(airRepository).getAirQuality(geographicalCoordinate.latitude, geographicalCoordinate.longitude)
    }
}
