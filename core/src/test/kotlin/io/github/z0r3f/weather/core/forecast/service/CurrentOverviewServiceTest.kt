package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.forecast.domain.CurrentDataMother
import io.github.z0r3f.weather.core.forecast.domain.CurrentMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CurrentOverviewServiceTest {

    val sut = CurrentOverviewService()

    @Test
    fun `generateOverviewMessage should return format data`() {
        val overviewMessage = sut.generateOverviewMessage(CURRENT_DATA)

        assertThat(overviewMessage).isEqualTo(EXPECTED)
    }

    @Test
    fun `generateOverviewMessage should skip feeling like when is same as real`() {
        val overviewMessage = sut.generateOverviewMessage(SAME_TEMP_DATA)

        assertThat(overviewMessage).isEqualTo(SAME_TEMP_EXPECTED)
    }

    @Test
    fun `generateOverviewMessage should skip wind gust when equal to wind speed`() {
        val overviewMessage = sut.generateOverviewMessage(SAME_WIND_DATA)

        assertThat(overviewMessage).isEqualTo(SAME_WIND_EXPECTED)
    }

    @Test
    fun `generateOverviewMessage should skip wind gust when equal to zero`() {
        val overviewMessage = sut.generateOverviewMessage(WITHOUT_GUST_DATA)

        assertThat(overviewMessage).isEqualTo(WITHOUT_GUST_EXPECTED)
    }

    private companion object {
        val CURRENT_DATA = CurrentDataMother().of()
        val SAME_TEMP_DATA = CurrentDataMother().of(
            current = CurrentMother().of(
                temperature = 23.05,
                feelsLike = 23.05,
            )
        )
        val SAME_WIND_DATA = CurrentDataMother().of(
            current = CurrentMother().of(
                windSpeed = 2.53,
                windGust = 2.53,
            )
        )
        val WITHOUT_GUST_DATA = CurrentDataMother().of(
            current = CurrentMother().of(
                windGust = 0.0,
            )
        )

        val EXPECTED = """
            *Madrid*
            🌤 23°C (22°C)
            ⬇️ 9 km/h (15 km/h)
            💧42% 🌀1021 hPa
            🌅 07:02 🌇 21:21
        """.trimIndent()

        val SAME_TEMP_EXPECTED = """
            *Madrid*
            🌤 23°C
            ⬇️ 9 km/h (15 km/h)
            💧42% 🌀1021 hPa
            🌅 07:02 🌇 21:21
        """.trimIndent()

        val SAME_WIND_EXPECTED = """
            *Madrid*
            🌤 23°C (22°C)
            ⬇️ 9 km/h
            💧42% 🌀1021 hPa
            🌅 07:02 🌇 21:21
        """.trimIndent()



        val WITHOUT_GUST_EXPECTED = """
            *Madrid*
            🌤 23°C (22°C)
            ⬇️ 9 km/h
            💧42% 🌀1021 hPa
            🌅 07:02 🌇 21:21
        """.trimIndent()
    }
}
