package io.github.z0r3f.weather.core.chat.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.chat.domain.Chat

data class GetChatsMessage(val hourOfDay: Int) : QueryMessage<List<Chat>>
