package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.port.ChatRepository
import me.fernando.telegram.event.MessageEvent
import me.fernando.weather.service.DelFavoriteOverviewService

class DelLocationCmd(
    private val chat: Chat,
    private val cityName: String,
    private val chatRepository: ChatRepository = locate(),
    private val delFavoriteOverviewService: DelFavoriteOverviewService = locate(),
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = locate(),
) : Command<Unit>() {
    override fun run() {
        chatRepository.getFavoriteLocations(chat).find {
            it.name.equals(cityName.trim(), ignoreCase = true)
        }?.let {
            chatRepository.removeFavoriteLocation(chat, it)
            // TODO Make a listener for this
            val response = delFavoriteOverviewService.generateOverviewMessage(it.toLocation())
            newMessageEventPublisher.publishEvent(MessageEvent(chat, response))
        }
    }
}
