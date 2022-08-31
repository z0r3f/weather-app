package me.fernando.telegram.domain.message

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

// ↓↓↓ Bad Request: BOT_COMMAND_INVALID (400) ↓↓↓
// Text of the command; 1-32 characters. Can contain only lowercase English letters, digits and underscores.
// https://core.telegram.org/bots/api#botcommand
enum class BotMessageType(val command: String, val description: String) {
    FORECAST("/forecast", "Overview next days (morning | afternoon | evening)"),
    HELP("/help", "Show this help"),
    ADD_LOCATION("/addlocation", "Add a new location"),
    DEL_LOCATION("/dellocation", "Remove a location"),
    ADD_ALERT("/addalert", "Add a new alert");

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

    fun botCommand(): BotMessage {
        return BotMessage(getCommandRaw(), description)
    }

    companion object {
        fun getAvailableCommands(): Set<BotMessage> {
            return values().map { it.botCommand() }.toSet()
        }
    }
}
