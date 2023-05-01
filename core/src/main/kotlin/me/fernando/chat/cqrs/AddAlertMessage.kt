package me.fernando.chat.cqrs

import me.fernando.chat.domain.Chat

data class AddAlertMessage(val chat: Chat, val hourOfDayRaw: String) : BaseAlertMessage(hourOfDayRaw)
