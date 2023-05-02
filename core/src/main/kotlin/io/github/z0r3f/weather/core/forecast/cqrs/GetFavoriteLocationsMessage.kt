package io.github.z0r3f.weather.core.forecast.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation

data class GetFavoriteLocationsMessage(
    val chat: Chat
) : QueryMessage<List<FavoriteLocation>>
