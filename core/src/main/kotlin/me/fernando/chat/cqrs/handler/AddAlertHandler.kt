package me.fernando.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.cqrs.AddAlertMessage
import me.fernando.chat.event.NewAlertEvent
import me.fernando.chat.port.ChatRepository

@Singleton
class AddAlertHandler(
    private val chatRepository: ChatRepository,
    private val eventPublisher: ApplicationEventPublisher<NewAlertEvent>,
) : ActionHandler<AddAlertMessage, Unit> {
    override fun handle(action: AddAlertMessage) {
        chatRepository.addAlert(action.chat, action.hourOfDay)
        eventPublisher.publishEventAsync(NewAlertEvent(action.chat, action.hourOfDay))
    }
}
