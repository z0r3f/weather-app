package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import io.github.z0r3f.weather.core.forecast.service.DelFavoriteOverviewService
import io.github.z0r3f.weather.core.telegram.cqrs.DeleteMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton

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
