package me.fernando.weather.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.repository.GetForecastRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

internal class GetForecastQryTest {

    private val getForecastRepository = mock(GetForecastRepository::class.java)

    @Test
    fun throw_exception_when_the_location_requested_is_invalid() {
        assertThrows(IllegalArgumentException::class.java) {
            GetForecastQry(CIUDAD_REAL, getForecastRepository).fakeRun()
        }
    }

    @Test
    fun return_the_forecast_when_the_location_requested_is_valid() {
        `when`(getForecastRepository.getForecast(MADRID)).thenReturn(MADRID_FORECAST)

        val forecast = GetForecastQry(MADRID, getForecastRepository).fakeRun()

        assertNotNull(forecast)
        assertEquals(MADRID_FORECAST, forecast)
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
