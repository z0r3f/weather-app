package me.fernando.weather.service

import me.fernando.weather.domain.Location
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AddFavoriteOverviewServiceTest {

    private val sut = AddFavoriteOverviewService()

    @Test
    fun testGenerateOverviewMessage() {
        val expected = "Added Tokio (35.68949, 139.69171) to favorites"
        val actual = sut.generateOverviewMessage(
            Location(
                name = "Tokio",
                latitude = 35.68949,
                longitude = 139.69171,
            )
        )
        assertEquals(expected, actual)
    }
}
