package io.github.z0r3f.weather.core.forecast.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AddAlertOverviewServiceTest {

    private val sut = AddAlertOverviewService()

    @Test
    fun testGenerateOverviewMessage() {
        val expected = "Added alert at 5:00 every day"
        val actual = sut.generateOverviewMessage(5)
        assertEquals(expected, actual)
    }
}
