package io.github.z0r3f.weather.core.forecast.service.comparator

import io.github.z0r3f.weather.core.forecast.domain.ForecastMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

internal class HourComparatorTest {

    val sut = HourComparator()

    @Test
    internal fun comparator_right_is_closer() {
        assertThat(sut.comparator(AT_TWO_OCLOCK).compare(FORECAST_AT_ONE, FORECAST_AT_TWO)).isEqualTo(1)
    }
    @Test
    internal fun comparator_equals_same_diff() {
        assertThat(sut.comparator(AT_TWO_OCLOCK).compare(FORECAST_AT_TWO, FORECAST_AT_TWO)).isEqualTo(0)
    }

    @Test
    internal fun comparator_left_is_closer() {
        assertThat(sut.comparator(AT_TWO_OCLOCK).compare(FORECAST_AT_TWO, FORECAST_AT_THREE)).isEqualTo(-1)
    }

    @Test
    internal fun comparator_same_diff() {
        assertThat(sut.comparator(AT_TWO_OCLOCK).compare(FORECAST_AT_ONE, FORECAST_AT_THREE)).isEqualTo(0)
    }

    private companion object {
        val AT_TWO_OCLOCK = LocalTime.of(2,0)

        val FORECAST_AT_ONE = ForecastMother().of(timeDataForecasted = LocalDate.now().atTime(1,0))
        val FORECAST_AT_TWO = ForecastMother().of(timeDataForecasted = LocalDate.now().atTime(2,0))
        val FORECAST_AT_THREE = ForecastMother().of(timeDataForecasted = LocalDate.now().atTime(3,0))
    }
}
