package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByCityNameMessage
import io.github.z0r3f.weather.core.forecast.domain.CurrentDataMother
import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinateMother
import io.github.z0r3f.weather.core.forecast.port.CurrentRepository
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

internal class GetCurrentByCityNameHandlerTest {

    private val directGeocodingRepository: DirectGeocodingRepository = mock()
    private val currentRepository: CurrentRepository = mock()

    private lateinit var sut : GetCurrentByCityNameHandler

    @BeforeEach
    internal fun setUp() {
        sut = GetCurrentByCityNameHandler(directGeocodingRepository, currentRepository)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(directGeocodingRepository, currentRepository)
    }

    @Test
    internal fun `should throw IllegalArgumentException when cityName is blank`() {
        val message = GetCurrentByCityNameMessage(" ")

        assertThrows(IllegalArgumentException::class.java) { sut.handle(message) }
    }

    @Test
    internal fun `should throw IllegalArgumentException when location not found`() {
        val message = GetCurrentByCityNameMessage("CityName")
        whenever(directGeocodingRepository.getCoordinatesByLocationName("CityName")).thenReturn(emptyList())

        assertThrows(IllegalArgumentException::class.java) { sut.handle(message) }
        verify(directGeocodingRepository).getCoordinatesByLocationName("CityName")
    }

    @Test
    internal fun `should return CurrentData when location found`() {
        val message = GetCurrentByCityNameMessage("CityName")
        val geographicalCoordinate = GeographicalCoordinateMother().of(name = "CityName")
        val currentData = CurrentDataMother().of(location = geographicalCoordinate.toLocation())
        whenever(directGeocodingRepository.getCoordinatesByLocationName("CityName")).thenReturn(listOf(geographicalCoordinate))
        whenever(currentRepository.getCurrent(geographicalCoordinate.latitude, geographicalCoordinate.longitude)).thenReturn(currentData)

        val result = sut.handle(message)

        assertEquals(currentData, result)
        verify(directGeocodingRepository).getCoordinatesByLocationName("CityName")
        verify(currentRepository).getCurrent(geographicalCoordinate.latitude, geographicalCoordinate.longitude)
    }
}
