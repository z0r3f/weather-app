package me.fernando.telegram.cqrs.handler

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.telegram.cqrs.NotSupportedQueryMessage
import me.fernando.telegram.event.MessageEvent

@Singleton
class NotSupportedHandler(
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = ServiceLocator.locate(),
): ActionHandler<NotSupportedQueryMessage, Unit> {
    override fun handle(action: NotSupportedQueryMessage) {
        newMessageEventPublisher.publishEvent(MessageEvent(action.chat, "Command not supported"))
    }
}
