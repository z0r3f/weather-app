package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.port.ChatRepository
import me.fernando.telegram.cqrs.DeleteMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory
import me.fernando.weather.service.DelFavoriteOverviewService

@Singleton
class DeleteHandler(
    private val chatRepository: ChatRepository,
    private val delFavoriteOverviewService: DelFavoriteOverviewService,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) : ActionHandler<DeleteMessage, Unit> {
    override fun handle(action: DeleteMessage) {
        val favoriteLocation = chatRepository.getFavoriteLocations(action.chat).find {
            it.name.equals(action.cityName.trim(), ignoreCase = true)
        }
        val feedback = if (favoriteLocation != null) {
            chatRepository.removeFavoriteLocation(action.chat, favoriteLocation)
            delFavoriteOverviewService.generateOverviewMessage(favoriteLocation.toLocation())
        } else {
            ErrorMessageFactory.notFoundFavoriteLocation()
        }
        newMessageEventPublisher.publishEventAsync(MessageEvent(action.chat, feedback))
    }

}
