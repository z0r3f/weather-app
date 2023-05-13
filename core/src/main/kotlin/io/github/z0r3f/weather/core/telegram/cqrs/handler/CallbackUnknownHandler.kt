package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.telegram.cqrs.CallbackUnknownMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory

@Singleton
class CallbackUnknownHandler(
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) : ActionHandler<CallbackUnknownMessage, Unit>{
    override fun handle(action: CallbackUnknownMessage) {
        newMessageEventPublisher.publishEventAsync(MessageEvent(action.chat, ErrorMessageFactory.callbackUnknown()))
    }
}
