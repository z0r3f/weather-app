package me.fernando.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import me.fernando.chat.cqrs.AddFavoriteLocationMessage
import me.fernando.chat.port.ChatRepository

@Singleton
class AddFavoriteLocationHandler(
    private val chatRepository: ChatRepository
) : ActionHandler<AddFavoriteLocationMessage, Unit>{
    override fun handle(action: AddFavoriteLocationMessage) {
        chatRepository.addFavoriteLocation(action.chat, action.favoriteLocation)
    }
}
