package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.core.forecast.domain.ForecastMother
import io.github.z0r3f.weather.core.forecast.domain.WeatherMother
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.ForecastDtoMother
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.WeatherDtoMother
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class ForecastMapperTest {

    private val weatherMapper = mock(WeatherMapper::class.java)
    private val sut = ForecastMapper(weatherMapper)

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(weatherMapper)
    }

    @Test
    fun toModel() {
        `when`(weatherMapper.toModel(WeatherDtoMother().of())).thenReturn(WeatherMother().of())

        val result = sut.toModel(ForecastDtoMother().of())

        assertEquals(ForecastMother().of(), result)
        verify(weatherMapper).toModel(WeatherDtoMother().of())
    }
}
