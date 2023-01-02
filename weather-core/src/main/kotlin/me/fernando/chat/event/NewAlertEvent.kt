package me.fernando.chat.event

import me.fernando.chat.domain.Chat

data class NewAlertEvent(val chat: Chat, val hourOfDay: Int)
