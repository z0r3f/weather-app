package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.forecast.domain.Location
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DelFavoriteOverviewServiceTest {

    private val sut = DelFavoriteOverviewService()

    @Test
    fun testGenerateOverviewMessage() {
        val expected = "Deleted Tokio from favorites"
        val actual = sut.generateOverviewMessage(
            Location(name = "Tokio")
        )
        assertEquals(expected, actual)
    }
}
