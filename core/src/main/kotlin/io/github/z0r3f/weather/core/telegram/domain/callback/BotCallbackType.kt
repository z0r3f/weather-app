package io.github.z0r3f.weather.core.telegram.domain.callback

import java.util.*

enum class BotCallbackType(val text: String) {
    ADD("📝 Add favorite"),
    DELETE("❌ Delete favorite"),
    DELETE_ALERT("❌ Delete alert"),
    UNKNOWN("❌ Unknown");

    companion object {
        fun fromString(value: String): BotCallbackType {
            return BotCallbackType.valueOf(value.uppercase(Locale.getDefault()))
        }
    }
}
