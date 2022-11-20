package me.fernando.chat.cqrs

import io.archimedesfw.cqrs.CommandMessage
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation

@Deprecated("Use me.fernando.telegram.cqrs.DeleteMessage instead ??")
data class RemoveFavoriteLocationMessage(
    val chat: Chat,
    val favoriteLocation: FavoriteLocation,
) : CommandMessage<Unit>
