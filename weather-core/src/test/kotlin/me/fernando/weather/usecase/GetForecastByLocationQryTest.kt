package me.fernando.weather.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.weather.domain.WeatherDataMother
import me.fernando.weather.port.ForecastRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class GetForecastByLocationQryTest {

    private val forecastRepository = mock(ForecastRepository::class.java)

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(forecastRepository)
    }

//    @Test
//    fun throw_exception_when_the_location_requested_is_invalid() {
//        assertThrows(IllegalArgumentException::class.java) {
//            GetForecastByFavoriteLocationQry(CIUDAD_REAL, forecastRepository).fakeRun()
//        }
//    }

    @Test
    fun return_the_forecast_when_the_location_requested_is_valid() {
        `when`(forecastRepository.getForecast(MADRID.latitude!!, MADRID.longitude!!)).thenReturn(MADRID_FORECAST)

        val forecast = GetForecastByFavoriteLocationQry(MADRID, forecastRepository).fakeRun()

        assertNotNull(forecast)
        assertEquals(MADRID_FORECAST, forecast)
        verify(forecastRepository).getForecast(MADRID.latitude!!, MADRID.longitude!!)
    }

    private companion object {
        val CIUDAD_REAL = FavoriteLocation(
            country = "Spain",
            name = "Ciudad Real"
        )
        val MADRID = FavoriteLocation(
            country = "Spain",
            name = "Madrid",
            latitude = 40.4167,
            longitude = -3.7033
        )
        val MADRID_FORECAST = WeatherDataMother().of()
    }
}
