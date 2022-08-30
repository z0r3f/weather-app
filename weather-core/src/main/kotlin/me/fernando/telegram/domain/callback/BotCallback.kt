package me.fernando.telegram.domain.callback

data class BotCallback(val type: BotCallbackType, val data: String) {
    fun toJson(): String = """{"text":"${type.text}","callback_data":"$data"}"""
}
