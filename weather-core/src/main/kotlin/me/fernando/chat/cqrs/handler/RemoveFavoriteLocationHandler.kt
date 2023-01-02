package me.fernando.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import me.fernando.chat.cqrs.RemoveFavoriteLocationMessage
import me.fernando.chat.port.ChatRepository

@Singleton
class RemoveFavoriteLocationHandler(
    private val chatRepository: ChatRepository
) : ActionHandler<RemoveFavoriteLocationMessage, Unit> {
    override fun handle(action: RemoveFavoriteLocationMessage) {
        chatRepository.removeFavoriteLocation(action.chat, action.favoriteLocation)
    }
}
