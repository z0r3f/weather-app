package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.event.NewFavoriteEvent
import me.fernando.chat.event.NewLocationEvent
import me.fernando.telegram.cqrs.AddLocationMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory
import me.fernando.weather.port.DirectGeocodingRepository

@Singleton
class AddLocationHandler(
    private val directGeocodingRepository: DirectGeocodingRepository,
    private val newFavoriteEventPublisher: ApplicationEventPublisher<NewFavoriteEvent>,
    private val newLocationEventPublisher: ApplicationEventPublisher<NewLocationEvent>,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
) : ActionHandler<AddLocationMessage, Unit> {
    override fun handle(action: AddLocationMessage) {
        val geographicalCoordinates = directGeocodingRepository.getCoordinatesByLocationName(action.cityName)

        if (geographicalCoordinates.isNotEmpty()) {
            val location = geographicalCoordinates.first().toLocation()

            newFavoriteEventPublisher.publishEventAsync(NewFavoriteEvent(action.chat, location))
            newLocationEventPublisher.publishEventAsync(NewLocationEvent(action.chat, location))
        } else {
            newMessageEventPublisher.publishEventAsync(
                MessageEvent(
                    chat = action.chat,
                    message = ErrorMessageFactory.coordinateIsMissing(action.cityName),
                )
            )
        }
    }
}
