package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.forecast.domain.AirDataMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AirOverviewServiceTest {

    val sut = AirOverviewService("0.00", "es")

    @Test
    internal fun generateOverviewMessage() {
        val overviewMessage = sut.generateOverviewMessage(AIR_DATA)

        assertThat(overviewMessage).isEqualTo(EXPECTED)
    }

    private companion object {
        val AIR_DATA = AirDataMother().of()

        val EXPECTED = """
            *Madrid*
            ```
            AQI:   █▒▒▒▒ 😊 1/5
            CO:    223,64 μg/m3
            NO:      0,00 μg/m3
            NO2:     0,49 μg/m3
            O3:     56,51 μg/m3
            SO2:     0,07 μg/m3
            PM2.5:   0,57 μg/m3
            PM10:    0,92 μg/m3
            NH3:     0,43 μg/m3
            ```
        """.trimIndent()
    }
}
