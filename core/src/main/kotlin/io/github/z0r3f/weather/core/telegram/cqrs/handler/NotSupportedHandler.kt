package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.telegram.cqrs.NotSupportedQueryMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory

@Singleton
class NotSupportedHandler(
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
): ActionHandler<NotSupportedQueryMessage, Unit> {
    override fun handle(action: NotSupportedQueryMessage) {
        newMessageEventPublisher.publishEventAsync(MessageEvent(action.chat, ErrorMessageFactory.commandNotSupported()))
    }
}
