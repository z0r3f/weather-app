package io.github.z0r3f.weather.core.telegram.domain.callback

data class BotCallback(val type: BotCallbackType, val data: String) {
    fun toJson(): String = """{"text":"${type.text}","callback_data":"$data"}"""
}
