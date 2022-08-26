package me.fernando.chat.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.chat.port.ChatRepository

class AddFavoriteLocationCmd(
    private val chat: Chat,
    private val favoriteLocation: FavoriteLocation,
    private val chatRepository: ChatRepository = locate()
): Command<Unit>() {
    override fun run() {
        chatRepository.addLocationFavorite(chat, favoriteLocation)
    }
}
