package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.chat.event.NewFavoriteEvent
import io.github.z0r3f.weather.core.chat.event.NewLocationEvent
import io.github.z0r3f.weather.core.telegram.cqrs.AddLocationMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository

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
