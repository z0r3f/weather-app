package io.github.z0r3f.weather.core.telegram.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.chat.domain.Chat

data class CallbackUnknownMessage(val chat: Chat) : QueryMessage<Unit>
