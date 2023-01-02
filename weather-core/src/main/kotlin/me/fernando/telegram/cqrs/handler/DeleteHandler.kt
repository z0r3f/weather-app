package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.port.ChatRepository
import me.fernando.telegram.cqrs.DeleteMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.weather.service.DelFavoriteOverviewService

@Singleton
class DeleteHandler(
    private val chatRepository: ChatRepository,
    private val delFavoriteOverviewService: DelFavoriteOverviewService,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
): ActionHandler<DeleteMessage, Unit> {
    override fun handle(action: DeleteMessage) {
        chatRepository.getFavoriteLocations(action.chat).find {
            it.name.equals(action.cityName.trim(), ignoreCase = true)
        }?.let {
            chatRepository.removeFavoriteLocation(action.chat, it)
            val response = delFavoriteOverviewService.generateOverviewMessage(it.toLocation())
            newMessageEventPublisher.publishEvent(MessageEvent(action.chat, response))
        } ?: newMessageEventPublisher.publishEvent(MessageEvent(action.chat, "Not found favorite location"))
    }
}
