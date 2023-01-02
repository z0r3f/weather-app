package me.fernando.telegram.event

import me.fernando.chat.domain.Chat
import me.fernando.telegram.domain.callback.BotCallback

data class MessageEvent(val chat: Chat, val message: String, val botCallbacks: List<BotCallback>? = null)
