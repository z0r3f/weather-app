package me.fernando.telegram.domain.callback

import java.util.*

enum class BotCallbackType(val text: String) {
    ADD("📝 Add as favorite"),
    DELETE("❌ Del as favorite"),
    UNKNOWN("❌ Unknown");

    companion object {
        fun fromString(value: String): BotCallbackType {
            return BotCallbackType.valueOf(value.uppercase(Locale.getDefault()))
        }
    }
}
