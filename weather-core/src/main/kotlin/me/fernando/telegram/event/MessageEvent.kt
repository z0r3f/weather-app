package me.fernando.telegram.event

import me.fernando.chat.domain.Chat

data class MessageEvent(val chat: Chat, val message: String)
