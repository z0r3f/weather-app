package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.event.NewFavoriteEvent
import me.fernando.chat.event.NewLocationEvent
import me.fernando.telegram.cqrs.AddLocationMessage
import me.fernando.weather.port.DirectGeocodingRepository

@Singleton
class AddLocationHandler(
    private val directGeocodingRepository: DirectGeocodingRepository,
    private val newFavoriteEventPublisher: ApplicationEventPublisher<NewFavoriteEvent>,
    private val newLocationEventPublisher: ApplicationEventPublisher<NewLocationEvent>,
) : ActionHandler<AddLocationMessage, Unit> {
    override fun handle(action: AddLocationMessage) {
        val location = directGeocodingRepository.getCoordinatesByLocationName(action.cityName).first().toLocation()

        newFavoriteEventPublisher.publishEventAsync(NewFavoriteEvent(action.chat, location))
        newLocationEventPublisher.publishEventAsync(NewLocationEvent(action.chat, location))
    }
}
