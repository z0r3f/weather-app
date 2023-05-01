package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.telegram.cqrs.NotSupportedQueryMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory

@Singleton
class NotSupportedHandler(
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
): ActionHandler<NotSupportedQueryMessage, Unit> {
    override fun handle(action: NotSupportedQueryMessage) {
        newMessageEventPublisher.publishEventAsync(MessageEvent(action.chat, ErrorMessageFactory.commandNotSupported()))
    }
}
