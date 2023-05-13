package io.github.z0r3f.weather.core.chat.port

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation

interface ChatRepository {
    fun addFavoriteLocation(chat: Chat, favoriteLocation: FavoriteLocation)

    fun removeFavoriteLocation(chat: Chat, favoriteLocation: FavoriteLocation)

    fun getFavoriteLocations(chat: Chat): List<FavoriteLocation>

    fun getFavoriteLocation(chat: Chat, cityName: String): FavoriteLocation?

    fun addAlert(chat: Chat, hourOfDay: Int)

    fun getAlerts(hourOfDay: Int): List<Chat>

    fun deleteAlert(chat: Chat, hourOfDay: Int)
}
