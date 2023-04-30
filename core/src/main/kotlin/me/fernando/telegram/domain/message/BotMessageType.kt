package me.fernando.telegram.domain.message

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

// ↓↓↓ Bad Request: BOT_COMMAND_INVALID (400) ↓↓↓
// Text of the command; 1-32 characters. Can contain only lowercase English letters, digits and underscores.
// https://core.telegram.org/bots/api#botcommand
enum class BotMessageType(val description: String, private val enabled: Boolean = true) {
    FORECAST(description = "Overview next days (morning | afternoon | evening)"),
    HELP(description = "Show this help"),
    ADD_LOCATION(description = "Add a location as a Favourite", enabled = false),
    DEL_LOCATION(description = "Remove a location as a Favourite", enabled = false),
    ADD_ALERT(description = "Add a new alert");

    private val commandRaw: String
        get() = name.lowercase().replace("_", "")

    val command: String
        get() = "/$commandRaw"

    @JsonValue
    fun toJson(): JsonNode? {
        return ObjectMapper().readTree(
            """
            {
                "command": "$commandRaw",
                "description": "$description"
            }
        """
        )
    }

    fun toMarkdown(): String = "*$command* - $description"

    fun toBotMessage(): BotMessage = BotMessage(commandRaw, description)

    companion object {
        fun getAvailableBotMessageType(): Set<BotMessageType> = values().filter { it.enabled }.toSet()
        fun getAvailableBotMessages(): Set<BotMessage> = getAvailableBotMessageType().map { it.toBotMessage() }.toSet()
    }
}
