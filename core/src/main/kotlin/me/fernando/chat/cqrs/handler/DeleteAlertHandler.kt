package me.fernando.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.cqrs.DeleteAlertMessage
import me.fernando.chat.event.RemoveAlertEvent
import me.fernando.chat.port.ChatRepository

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
