package me.fernando.chat.cqrs

import me.fernando.chat.domain.Chat

data class DeleteAlertMessage(val chat: Chat, val hourOfDayRaw: String) : BaseAlertMessage(hourOfDayRaw)
