package io.github.z0r3f.weather.core.forecast.handler

import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.forecast.cqrs.GetForecastByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.cqrs.handler.GetForecastByFavoriteLocationHandler
import io.github.z0r3f.weather.core.forecast.domain.WeatherDataMother
import io.github.z0r3f.weather.core.forecast.port.ForecastRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetForecastByFavoriteLocationHandlerTest {

    private val forecastRepository: ForecastRepository = mock()

    private lateinit var sut: GetForecastByFavoriteLocationHandler

    @BeforeEach
    fun setUp() {
        sut = GetForecastByFavoriteLocationHandler(forecastRepository)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(forecastRepository)
    }

    @Test
    fun return_the_forecast_when_the_location_requested_is_valid() {
        whenever(forecastRepository.getForecast(MADRID.latitude!!, MADRID.longitude!!)).thenReturn(MADRID_FORECAST)

        val forecast = sut.handle(GetForecastByFavoriteLocationMessage(MADRID))

        assertNotNull(forecast)
        assertEquals(MADRID_FORECAST, forecast)
        verify(forecastRepository).getForecast(MADRID.latitude!!, MADRID.longitude!!)
    }

    private companion object {
        val MADRID = FavoriteLocation(
            country = "Spain",
            name = "Madrid",
            latitude = 40.4167,
            longitude = -3.7033
        )
        val MADRID_FORECAST = WeatherDataMother().of()
    }
}
