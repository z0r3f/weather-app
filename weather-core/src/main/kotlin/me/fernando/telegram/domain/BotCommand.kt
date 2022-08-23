package me.fernando.telegram.domain

enum class BotCommand(val command: String, val description: String) {
    FORECAST("/forecast", "Overview next days (morning | afternoon | evening)"),
    HELP("/help", "Show this help"),
}
