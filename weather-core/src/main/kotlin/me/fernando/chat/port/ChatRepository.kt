package me.fernando.chat.port

import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation

interface ChatRepository {
    fun addLocationFavorite(chat: Chat, favoriteLocation: FavoriteLocation)

    fun removeLocationFavorite(chat: Chat, favoriteLocation: FavoriteLocation)
}
