package me.fernando.weather.service

import me.fernando.telegram.domain.BotCommand
import org.junit.jupiter.api.Test

internal class FormatHelpTest {

    @Test
    fun format() {
        val actual = FormatHelp.overview()

        BotCommand.values().forEach {
            assert(actual.contains(it.command))
            assert(actual.contains(it.description))
        }
    }
}
