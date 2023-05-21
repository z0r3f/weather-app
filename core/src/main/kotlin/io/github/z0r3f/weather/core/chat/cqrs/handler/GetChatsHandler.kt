package io.github.z0r3f.weather.core.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.chat.cqrs.GetChatsMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import jakarta.inject.Singleton

@Singleton
class GetChatsHandler(
    private val chatRepository: ChatRepository,
) : ActionHandler<GetChatsMessage, List<Chat>> {
    override fun handle(action: GetChatsMessage): List<Chat> = chatRepository.getAlerts(action.hourOfDay)
}
