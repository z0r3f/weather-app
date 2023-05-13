package io.github.z0r3f.weather.core.chat.cqrs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BaseAlertMessageTest {

    @Test
    fun `valid hour of day should be parsed correctly`() {
        val hourOfDayRaw = "12"

        val alertMessage = object : BaseAlertMessage(hourOfDayRaw) {
            override fun toString(): String = "$hourOfDay"
        }

        assertEquals(12, alertMessage.hourOfDay)
    }

    @Test
    fun `invalid hour of day should throw exception`() {
        val hourOfDayRaw = "24"

        val exception = assertThrows<IllegalArgumentException> {
            object : BaseAlertMessage(hourOfDayRaw) {
                override fun toString(): String = "$hourOfDay"
            }
        }

        assertEquals(
            "Hour of day must be between 0 and 23",
            exception.message
        )
    }

    @Test
    fun `non-numeric hour of day should throw exception`() {
        val hourOfDayRaw = "not a number"

        val exception = assertThrows<IllegalArgumentException> {
            object : BaseAlertMessage(hourOfDayRaw) {
                override fun toString(): String = "$hourOfDay"
            }
        }

        assertEquals(
            "Invalid hour of day: \"not a number\". Should be an integer between 0 and 23",
            exception.message
        )
    }
}
