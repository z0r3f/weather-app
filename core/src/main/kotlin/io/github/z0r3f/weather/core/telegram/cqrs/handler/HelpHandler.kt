package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.service.HelpOverviewService
import io.github.z0r3f.weather.core.telegram.cqrs.HelpQueryMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.generateOverviewMessage
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton

@Singleton
class HelpHandler(
    private val helpOverviewService: HelpOverviewService,
    private val eventPublisher: ApplicationEventPublisher<MessageEvent>,
): ActionHandler<HelpQueryMessage, Unit> {
    override fun handle(action: HelpQueryMessage) {
        val helpMessage = helpOverviewService.generateOverviewMessage()
        eventPublisher.publishEventAsync(MessageEvent(action.chat, helpMessage))
    }
}
