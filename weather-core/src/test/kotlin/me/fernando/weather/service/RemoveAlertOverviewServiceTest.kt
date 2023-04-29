package me.fernando.weather.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemoveAlertOverviewServiceTest {

    private val sut = RemoveAlertOverviewService()

    @Test
    fun testGenerateOverviewMessage() {
        val expected = "Deleted alert at 5:00 every day"
        val actual = sut.generateOverviewMessage(5)
        assertEquals(expected, actual)
    }
}
