package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.ForecastMessage
import io.github.z0r3f.weather.core.forecast.event.RequestForecastEvent
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton

@Singleton
class ForecastHandler(
    private val requestForecastEvent: ApplicationEventPublisher<RequestForecastEvent>,
) : ActionHandler<ForecastMessage, Unit>{
    override fun handle(action: ForecastMessage) {
        requestForecastEvent.publishEventAsync(RequestForecastEvent(action.chat, action.cityName, action.hourOfDay))
    }
}
