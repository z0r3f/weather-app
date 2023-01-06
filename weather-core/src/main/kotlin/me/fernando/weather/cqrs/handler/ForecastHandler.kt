package me.fernando.weather.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.weather.cqrs.ForecastMessage
import me.fernando.weather.event.RequestForecastEvent

@Singleton
class ForecastHandler(
    private val requestForecastEvent: ApplicationEventPublisher<RequestForecastEvent>,
) : ActionHandler<ForecastMessage, Unit>{
    override fun handle(action: ForecastMessage) {
        requestForecastEvent.publishEventAsync(RequestForecastEvent(action.chat, action.cityName, action.hourOfDay))
    }
}
