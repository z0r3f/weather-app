package me.fernando.chat.event

import me.fernando.chat.domain.Chat

data class RemoveAlertEvent(val chat: Chat, val hourOfDay: Int)
