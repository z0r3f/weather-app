package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.forecast.domain.CurrentDataMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CurrentOverviewServiceTest {

    val sut = CurrentOverviewService()

    @Test
    fun generateOverviewMessage() {
        val overviewMessage = sut.generateOverviewMessage(CURRENT_DATA)

        assertThat(overviewMessage).isEqualTo(EXPECTED)
    }

    private companion object {
        val CURRENT_DATA = CurrentDataMother().of()

        val EXPECTED = """
            *Madrid*
            🌤 23°C (23°C)
            ⬇️ 9 km/h (15 km/h)
            💧42% 🌀1021.0 hPa
            🌅 07:02 🌇 21:21
        """.trimIndent()
    }
}
