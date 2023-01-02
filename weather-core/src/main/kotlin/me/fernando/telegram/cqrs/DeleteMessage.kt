package me.fernando.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage
import me.fernando.chat.domain.Chat

data class DeleteMessage(val chat: Chat, val cityName: String) : CommandMessage<Unit>
