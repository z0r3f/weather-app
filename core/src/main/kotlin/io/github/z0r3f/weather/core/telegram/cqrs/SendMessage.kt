package io.github.z0r3f.weather.core.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback

data class SendMessage(
    val chat: Chat,
    val message: String,
    val botCallbacks: List<BotCallback>? = null,
) : CommandMessage<Unit>
