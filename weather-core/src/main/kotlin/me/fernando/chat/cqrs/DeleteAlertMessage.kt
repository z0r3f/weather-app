package me.fernando.chat.cqrs

import io.archimedesfw.cqrs.CommandMessage
import me.fernando.chat.domain.Chat

data class DeleteAlertMessage(val chat: Chat, val hourOfDayRaw: String) : CommandMessage<Unit>
