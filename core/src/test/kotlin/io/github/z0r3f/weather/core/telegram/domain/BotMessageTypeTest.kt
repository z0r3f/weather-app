package io.github.z0r3f.weather.core.telegram.domain

import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

internal class BotMessageTypeTest {

    @Test
    internal fun should_be_able_to_build_a_Json_with_all_the_commands() {
        val commandsInJson = BotMessageType.values().map { it.toJson() }.toString()

        JSONAssert.assertEquals(ALL_COMMANDS_IN_JSON, commandsInJson, false)
    }

    @Test
    internal fun should_only_return_enable_commands() {
        val result = BotMessageType.getAvailableBotMessages()

        assertEquals(ENABLED_COMMANDS, result)
    }

    private companion object {
        const val ALL_COMMANDS_IN_JSON: String = """
            [
                {
                    "command": "forecast", 
                    "description": "Overview next days (morning | afternoon | evening)"
                },
                {
                    "command": "help", 
                    "description": "Show this help"
                },
                {
                    "command": "addlocation", 
                    "description": "Add a location as a Favourite"
                },
                {
                    "command": "dellocation", 
                    "description": "Remove a location as a Favourite"
                },
                {
                    "command": "addalert", 
                    "description": "Add a new alert"
                }
            ]
        """

        val ENABLED_COMMANDS = setOf(
            FORECAST.toBotMessage(),
            HELP.toBotMessage(),
            ADD_ALERT.toBotMessage(),
        )
    }
}
