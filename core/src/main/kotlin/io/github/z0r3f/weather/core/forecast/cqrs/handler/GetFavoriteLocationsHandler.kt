package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage

@Singleton
class GetFavoriteLocationsHandler(
    private val chatRepository: ChatRepository,
) : ActionHandler<io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage, List<FavoriteLocation>>{
    override fun handle(action: io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage): List<FavoriteLocation> {
        return chatRepository.getFavoriteLocations(action.chat)
    }
}
