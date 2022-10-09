package me.fernando.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage
import me.fernando.chat.domain.Chat

data class HelpQueryMessage(val chat: Chat) : CommandMessage<Unit>
