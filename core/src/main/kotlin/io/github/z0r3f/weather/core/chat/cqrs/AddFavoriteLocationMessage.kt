package io.github.z0r3f.weather.core.chat.cqrs

import io.archimedesfw.cqrs.CommandMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation

data class AddFavoriteLocationMessage(
    val chat: Chat,
    val favoriteLocation: FavoriteLocation,
) : CommandMessage<Unit>
