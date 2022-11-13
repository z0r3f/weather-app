package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Query
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.chat.port.ChatRepository

class GetForecastByFavoriteLocationsQry(
    private val chat: Chat,
    private val chatRepository: ChatRepository = locate()
): Query<List<FavoriteLocation>>() {

    override fun run(): List<FavoriteLocation> {
        return chatRepository.getFavoriteLocations(chat)
    }
}
