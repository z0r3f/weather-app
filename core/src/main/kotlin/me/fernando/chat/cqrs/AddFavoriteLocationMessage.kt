package me.fernando.chat.cqrs

import io.archimedesfw.cqrs.CommandMessage
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation

data class AddFavoriteLocationMessage(
    val chat: Chat,
    val favoriteLocation: FavoriteLocation,
) : CommandMessage<Unit>
