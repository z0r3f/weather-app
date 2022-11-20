package me.fernando.weather.cqrs.handler

import me.fernando.weather.cqrs.GetForecastByCityNameMessage
import me.fernando.weather.domain.GeographicalCoordinateMother
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.port.DirectGeocodingRepository
import me.fernando.weather.port.ForecastRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetForecastByCityNameHandlerTest {

    private val directGeocodingRepository: DirectGeocodingRepository = mock()
    private val forecastRepository: ForecastRepository = mock()

    private lateinit var sut: GetForecastByCityNameHandler

    @BeforeEach
    fun setUp() {
        sut = GetForecastByCityNameHandler(directGeocodingRepository, forecastRepository)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(directGeocodingRepository, forecastRepository)
    }

    @Test
    fun throw_exception_when_the_location_requested_is_invalid() {
        assertThrows(IllegalArgumentException::class.java) {
            sut.handle(GetForecastByCityNameMessage(""))
        }
    }

    @Test
    fun return_the_forecast_when_the_location_requested_is_valid() {
        whenever(directGeocodingRepository.getCoordinatesByLocationName(LISBON)).thenReturn(listOf(LISBON_COORDINATES))
        whenever(forecastRepository.getForecast(LISBON_COORDINATES.latitude, LISBON_COORDINATES.longitude)).thenReturn(
            LISBON_FORECAST
        )

        val forecast = sut.handle(GetForecastByCityNameMessage(LISBON))

        assertNotNull(forecast)
        assertEquals(
            LISBON_FORECAST.copy(
                location = LISBON_FORECAST.location!!.copy(
                    name = LISBON,
                )
            ), forecast
        )
        verify(directGeocodingRepository).getCoordinatesByLocationName(LISBON)
        verify(forecastRepository).getForecast(LISBON_COORDINATES.latitude, LISBON_COORDINATES.longitude)
    }

    private companion object {
        const val LISBON = "Lisbon"
        val LISBON_COORDINATES = GeographicalCoordinateMother().of()
        val LISBON_FORECAST = WeatherDataMother().of()
    }
}
