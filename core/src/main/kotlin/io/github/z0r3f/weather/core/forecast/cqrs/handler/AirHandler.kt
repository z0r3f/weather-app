package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.AirMessage
import io.github.z0r3f.weather.core.forecast.event.RequestAirEvent
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton

@Singleton
class AirHandler(
    private val requestAirEvent: ApplicationEventPublisher<RequestAirEvent>,
) : ActionHandler<AirMessage, Unit>{
    override fun handle(action: AirMessage) {
        requestAirEvent.publishEventAsync(RequestAirEvent(action.chat, action.cityName))
    }
}
