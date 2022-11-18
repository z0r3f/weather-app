package me.fernando.telegram.cqrs.handler

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.event.RequestHelpDataEvent
import me.fernando.telegram.cqrs.HelpQueryMessage

@Singleton
class HelpHandler(
    private val requestHelpDataEventPublisher: ApplicationEventPublisher<RequestHelpDataEvent> = ServiceLocator.locate(),
): ActionHandler<HelpQueryMessage, Unit> {
    override fun handle(action: HelpQueryMessage) {
        requestHelpDataEventPublisher.publishEvent(RequestHelpDataEvent(action.chat))
    }
}
