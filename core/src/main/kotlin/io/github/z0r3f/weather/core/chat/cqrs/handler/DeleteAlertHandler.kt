package io.github.z0r3f.weather.core.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.chat.cqrs.DeleteAlertMessage
import io.github.z0r3f.weather.core.chat.event.RemoveAlertEvent
import io.github.z0r3f.weather.core.chat.port.ChatRepository

@Singleton
class DeleteAlertHandler(
    private val chatRepository: ChatRepository,
    private val eventPublisher: ApplicationEventPublisher<RemoveAlertEvent>
) : ActionHandler<DeleteAlertMessage, Unit> {
    override fun handle(action: DeleteAlertMessage) {
        chatRepository.deleteAlert(action.chat, action.hourOfDay)
        eventPublisher.publishEventAsync(RemoveAlertEvent(action.chat, action.hourOfDay))
    }
}
