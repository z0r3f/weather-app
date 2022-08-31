package me.fernando.telegram.domain

import me.fernando.telegram.domain.message.BotMessageType
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert.assertEquals

internal class BotMessageTypeTest {

    @Test
    fun should_be_able_to_build_a_Json_with_all_the_commands() {
        val commandsInJson = BotMessageType.values().map { it.getJson() }.toString()

        assertEquals(ACTIVE_COMMANDS, commandsInJson, false)
    }

    private companion object {
        const val ACTIVE_COMMANDS: String = """
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
                    "description": "Add a new location"
                },
                {
                    "command": "dellocation", 
                    "description": "Remove a location"
                },
                {
                    "command": "addalert", 
                    "description": "Add a new alert"
                }
            ]
        """
    }
}
