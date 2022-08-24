package me.fernando.telegram.domain

import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert.assertEquals

internal class BotCommandTest {

    @Test
    fun should_be_able_to_build_a_Json_with_all_the_commands() {
        val commandsInJson = BotCommand.values().map { it.getJson() }.toString()

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
                }
            ]
        """
    }
}
