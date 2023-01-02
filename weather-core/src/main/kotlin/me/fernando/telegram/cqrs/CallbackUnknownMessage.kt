package me.fernando.telegram.cqrs

import io.archimedesfw.cqrs.QueryMessage
import me.fernando.chat.domain.Chat

data class CallbackUnknownMessage(val chat: Chat) : QueryMessage<Unit>
