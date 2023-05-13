package io.github.z0r3f.weather.core.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.chat.cqrs.AddAlertMessage
import io.github.z0r3f.weather.core.chat.event.NewAlertEvent
import io.github.z0r3f.weather.core.chat.port.ChatRepository

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
