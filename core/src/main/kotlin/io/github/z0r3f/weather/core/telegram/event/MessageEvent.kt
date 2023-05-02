package io.github.z0r3f.weather.core.telegram.event

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback

data class MessageEvent(val chat: Chat, val message: String, val botCallbacks: List<BotCallback>? = null)
