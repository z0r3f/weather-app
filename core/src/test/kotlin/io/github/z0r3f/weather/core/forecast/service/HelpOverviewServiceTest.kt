package io.github.z0r3f.weather.core.forecast.service

import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType
import io.github.z0r3f.weather.core.util.generateOverviewMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HelpOverviewServiceTest {

    private val sut = HelpOverviewService()

    @Test
    internal fun generateHelpOverviewMessage() {
        val actual = sut.generateOverviewMessage()

        BotMessageType.getAvailableBotMessageType().forEach {
            assert(actual.contains(it.command))
            assert(actual.contains(it.description))
        }
    }

    @Test
    internal fun shouldReturnRightLines() {
        val expected = 3

        val actual = sut.generateOverviewMessage()

        val commands = actual.lines().filter { it.startsWith("*/") }
        assertEquals(expected, commands.size)
    }

    @Test
    internal fun shouldReturnThisResult() {
        val expected = """
            *I can help you with the weather 🌤 in any city in the world 🗺. Just type the name of the city and I will tell you the weather.*

            Use the following commands:

            */forecast* - Overview next days (morning | afternoon | evening)

            */help* - Show this help

            */addalert* - Add a new alert
        """.trimIndent()

        val actual = sut.generateOverviewMessage()

        assertEquals(expected, actual)
    }
}
