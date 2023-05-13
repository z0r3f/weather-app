package io.github.z0r3f.weather.core.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.chat.cqrs.AddFavoriteLocationMessage
import io.github.z0r3f.weather.core.chat.port.ChatRepository

@Singleton
class AddFavoriteLocationHandler(
    private val chatRepository: ChatRepository
) : ActionHandler<AddFavoriteLocationMessage, Unit>{
    override fun handle(action: AddFavoriteLocationMessage) {
        chatRepository.addFavoriteLocation(action.chat, action.favoriteLocation)
    }
}
