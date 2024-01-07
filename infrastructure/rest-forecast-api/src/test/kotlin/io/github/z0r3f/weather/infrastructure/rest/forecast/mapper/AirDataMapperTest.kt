package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.core.forecast.domain.AirDataMother
import io.github.z0r3f.weather.core.forecast.domain.AirMother
import io.github.z0r3f.weather.core.forecast.domain.LocationMother
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.AirDataDtoMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class AirDataMapperTest {

    private lateinit var locationMapper: LocationMapper

    private lateinit var sut: AirDataMapper

    @BeforeEach
    internal fun setUp() {
        locationMapper = mock()
        sut = AirDataMapper(locationMapper)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(locationMapper)
    }

    @Test
    internal fun toModel() {
        whenever(locationMapper.toModel(DTO)).thenReturn(LOCATION)

        val result = sut.toModel(DTO)

        assertThat(result).isEqualTo(EXPECTED)
        verify(locationMapper).toModel(DTO)
    }

    private companion object {
        val DTO = AirDataDtoMother().of()
        val LOCATION = LocationMother().of()
        val AIR = AirMother().of()
        val EXPECTED = AirDataMother().of()
    }
}
