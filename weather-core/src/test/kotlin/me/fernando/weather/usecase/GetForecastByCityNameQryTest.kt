package me.fernando.weather.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.weather.domain.GeographicalCoordinateMother
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.port.DirectGeocodingRepository
import me.fernando.weather.port.ForecastRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class GetForecastByCityNameQryTest {

    private val directGeocodingRepository = mock(DirectGeocodingRepository::class.java)
    private val forecastRepository = mock(ForecastRepository::class.java)

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(directGeocodingRepository)
        verifyNoMoreInteractions(forecastRepository)
    }

    @Test
    fun throw_exception_when_the_location_requested_is_invalid() {
        assertThrows(IllegalArgumentException::class.java) {
            GetForecastByCityNameQry("", directGeocodingRepository, forecastRepository).fakeRun()
        }
    }

    @Test
    fun return_the_forecast_when_the_location_requested_is_valid() {
        `when`(directGeocodingRepository.getCoordinatesByLocationName(LISBON)).thenReturn(listOf(LISBON_COORDINATES))
        `when`(forecastRepository.getForecast(LISBON_COORDINATES.latitude, LISBON_COORDINATES.longitude)).thenReturn(LISBON_FORECAST)

        val forecast = GetForecastByCityNameQry(LISBON, directGeocodingRepository, forecastRepository).fakeRun()

        assertNotNull(forecast)
        assertEquals(LISBON_FORECAST.copy(
            location = LISBON_FORECAST.location!!.copy(
                name = LISBON,
            )
        ), forecast)
        verify(directGeocodingRepository).getCoordinatesByLocationName(LISBON)
        verify(forecastRepository).getForecast(LISBON_COORDINATES.latitude, LISBON_COORDINATES.longitude)
    }

    private companion object {
        const val LISBON = "Lisbon"
        val LISBON_COORDINATES = GeographicalCoordinateMother().of()
        val LISBON_FORECAST = WeatherDataMother().of()
    }
}
