package me.fernando.weather.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.repository.ForecastRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class GetForecastByLocationQryTest {

    private val forecastRepository = mock(ForecastRepository::class.java)

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(forecastRepository)
    }

    @Test
    fun throw_exception_when_the_location_requested_is_invalid() {
        assertThrows(IllegalArgumentException::class.java) {
            GetForecastByLocationQry(CIUDAD_REAL, forecastRepository).fakeRun()
        }
    }

    @Test
    fun return_the_forecast_when_the_location_requested_is_valid() {
        `when`(forecastRepository.getForecast(MADRID.latitude!!, MADRID.longitude!!)).thenReturn(MADRID_FORECAST)

        val forecast = GetForecastByLocationQry(MADRID, forecastRepository).fakeRun()

        assertNotNull(forecast)
        assertEquals(MADRID_FORECAST, forecast)
        verify(forecastRepository).getForecast(MADRID.latitude!!, MADRID.longitude!!)
    }

    private companion object {
        val CIUDAD_REAL = Location(
            country = "Spain",
            name = "Ciudad Real"
        )
        val MADRID = Location(
            country = "Spain",
            name = "Madrid",
            latitude = 40.4167,
            longitude = -3.7033
        )
        val MADRID_FORECAST = WeatherDataMother().of()
    }
}
