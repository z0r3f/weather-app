package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.CurrentMessage
import io.github.z0r3f.weather.core.forecast.event.RequestCurrentEvent
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton

@Singleton
class CurrentHandler(
    private val requestCurrentEvent: ApplicationEventPublisher<RequestCurrentEvent>,
) : ActionHandler<CurrentMessage, Unit>{
    override fun handle(action: CurrentMessage) {
        requestCurrentEvent.publishEventAsync(RequestCurrentEvent(action.chat, action.cityName))
    }
}
