package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.NewFavoriteEvent
import me.fernando.chat.event.NewLocationEvent
import me.fernando.weather.port.DirectGeocodingRepository

class AddLocationCmd(
    private val chat: Chat,
    private val cityName: String,
    private val directGeocodingRepository: DirectGeocodingRepository = locate(),
    private val newFavoriteEventPublisher: ApplicationEventPublisher<NewFavoriteEvent> = locate(),
    private val newLocationEventPublisher: ApplicationEventPublisher<NewLocationEvent> = locate(),
) : Command<Unit>() {
    override fun run() {
        val location = directGeocodingRepository.getCoordinatesByLocationName(cityName).first().toLocation()

        newFavoriteEventPublisher.publishEvent(NewFavoriteEvent(chat, location))
        newLocationEventPublisher.publishEvent(NewLocationEvent(chat, location))
    }
}
