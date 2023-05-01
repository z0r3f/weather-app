package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.telegram.cqrs.CallbackUnknownMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory

@Singleton
class CallbackUnknownHandler(
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) : ActionHandler<CallbackUnknownMessage, Unit>{
    override fun handle(action: CallbackUnknownMessage) {
        newMessageEventPublisher.publishEventAsync(MessageEvent(action.chat, ErrorMessageFactory.callbackUnknown()))
    }
}
