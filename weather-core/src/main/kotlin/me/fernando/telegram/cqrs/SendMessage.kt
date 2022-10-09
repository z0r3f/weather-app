package me.fernando.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage
import me.fernando.chat.domain.Chat
import me.fernando.telegram.domain.callback.BotCallback

data class SendMessage(
    val chat: Chat,
    val message: String,
    val botCallbacks: List<BotCallback>? = null,
) : CommandMessage<Unit>
