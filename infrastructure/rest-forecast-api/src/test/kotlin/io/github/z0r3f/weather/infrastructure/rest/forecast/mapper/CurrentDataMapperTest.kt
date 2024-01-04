package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.core.forecast.domain.CurrentDataMother
import io.github.z0r3f.weather.core.forecast.domain.LocationMother
import io.github.z0r3f.weather.core.forecast.domain.WeatherMother
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.CurrentDataDtoMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class CurrentDataMapperTest {

    private lateinit var locationMapper: LocationMapper
    private lateinit var weatherMapper: WeatherMapper

    private lateinit var sut: CurrentDataMapper

    @BeforeEach
    internal fun setUp() {
        locationMapper = mock()
        weatherMapper = mock()
        sut = CurrentDataMapper(locationMapper, weatherMapper)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(locationMapper, weatherMapper)
    }

    @Test
    internal fun toModel() {
        whenever(locationMapper.toModel(DTO)).thenReturn(LOCATION)
        whenever(weatherMapper.toModel(DTO.weather)).thenReturn(WEATHER)

        val result = sut.toModel(DTO)

        assertThat(result).isEqualTo(EXPECTED)
        verify(locationMapper).toModel(DTO)
        verify(weatherMapper).toModel(DTO.weather)
    }

    private companion object {
        val DTO = CurrentDataDtoMother().of()
        val LOCATION = LocationMother().of()
        val WEATHER = WeatherMother().of()
        val EXPECTED = CurrentDataMother().of()
    }
}
