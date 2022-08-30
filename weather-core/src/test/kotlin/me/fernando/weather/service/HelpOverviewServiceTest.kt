package me.fernando.weather.service

import me.fernando.telegram.domain.message.BotMessageType
import me.fernando.util.generateOverviewMessage
import org.junit.jupiter.api.Test

internal class HelpOverviewServiceTest {

    private val sut = HelpOverviewService()

    @Test
    fun generateHelpOverviewMessage() {
        val actual = sut.generateOverviewMessage()

        BotMessageType.values().forEach {
            assert(actual.contains(it.command))
            assert(actual.contains(it.description))
        }
    }
}