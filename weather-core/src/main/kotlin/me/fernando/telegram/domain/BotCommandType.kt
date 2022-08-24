package me.fernando.telegram.domain

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

enum class BotCommandType(val command: String, val description: String) {
    FORECAST("/forecast", "Overview next days (morning | afternoon | evening)"),
    HELP("/help", "Show this help");

    private fun getCommandRaw(): String {
        return command.replace("/", "")
    }

    @JsonValue
    fun getJson(): JsonNode? {
        return ObjectMapper().readTree("""
            {
                "command": "${getCommandRaw()}",
                "description": "$description"
            }
        """)
    }

    fun botCommand(): BotCommand {
        return BotCommand(getCommandRaw(), description)
    }

    companion object {
        fun getAvailableCommands(): Set<BotCommand> {
            return values().map { it.botCommand() }.toSet()
        }
    }
}
