package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.telegram.cqrs.HelpQueryMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.generateOverviewMessage
import me.fernando.weather.service.HelpOverviewService

@Singleton
class HelpHandler(
    private val helpOverviewService: HelpOverviewService,
    private val eventPublisher: ApplicationEventPublisher<MessageEvent>,
): ActionHandler<HelpQueryMessage, Unit> {
    override fun handle(action: HelpQueryMessage) {
        val helpMessage = helpOverviewService.generateOverviewMessage()
        eventPublisher.publishEvent(MessageEvent(action.chat, helpMessage))
    }
}
