package me.fernando.chat.port

import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation

interface ChatRepository {
    fun addFavoriteLocation(chat: Chat, favoriteLocation: FavoriteLocation)

    fun removeFavoriteLocation(chat: Chat, favoriteLocation: FavoriteLocation)

    fun getFavoriteLocations(chat: Chat): List<FavoriteLocation>

    fun getFavoriteLocation(chat: Chat, cityName: String): FavoriteLocation?
}
