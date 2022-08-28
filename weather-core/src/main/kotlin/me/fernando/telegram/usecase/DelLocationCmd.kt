package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.chat.port.ChatRepository
import me.fernando.weather.service.DelFavoriteOverviewService

class DelLocationCmd(
    private val chat: Chat,
    private val cityName: String,
    private val chatRepository: ChatRepository = locate(),
    private val delFavoriteOverviewService: DelFavoriteOverviewService = locate(),
) : Command<Unit>() {
    override fun run() {
        chatRepository.getFavoriteLocations(chat).find {
            it.name.equals(cityName.trim(), ignoreCase = true)
        }?.let {
            chatRepository.removeFavoriteLocation(chat, it)
            val response = delFavoriteOverviewService.generateOverviewMessage(it.toLocation())
            run(SendMessageCmd(chat, response))
        }
    }
}
