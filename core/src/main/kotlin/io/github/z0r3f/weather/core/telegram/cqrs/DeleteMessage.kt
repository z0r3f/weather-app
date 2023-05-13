package io.github.z0r3f.weather.core.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage
import io.github.z0r3f.weather.core.chat.domain.Chat

data class DeleteMessage(val chat: Chat, val cityName: String) : CommandMessage<Unit>
