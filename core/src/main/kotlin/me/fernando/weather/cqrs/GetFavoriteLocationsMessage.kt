package me.fernando.weather.cqrs

import io.archimedesfw.cqrs.QueryMessage
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation

data class GetFavoriteLocationsMessage(
    val chat: Chat
) : QueryMessage<List<FavoriteLocation>>
