package me.fernando.weather.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.chat.port.ChatRepository
import me.fernando.weather.cqrs.GetFavoriteLocationsMessage

@Singleton
class GetFavoriteLocationsHandler(
    private val chatRepository: ChatRepository,
) : ActionHandler<GetFavoriteLocationsMessage, List<FavoriteLocation>>{
    override fun handle(action: GetFavoriteLocationsMessage): List<FavoriteLocation> {
        return chatRepository.getFavoriteLocations(action.chat)
    }
}
