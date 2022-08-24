package me.fernando.telegram.domain

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

enum class BotCommand(val command: String, val description: String) {
    FORECAST("/forecast", "Overview next days (morning | afternoon | evening)"),
    HELP("/help", "Show this help");

    @JsonValue
    fun getJson(): JsonNode? {
        return ObjectMapper().readTree("""
            {
                "command": "${command.replace("/", "")}",
                "description": "$description"
            }
        """)
    }
}
