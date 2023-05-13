package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.event.NewFavoriteEvent
import io.github.z0r3f.weather.core.chat.event.NewLocationEvent
import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinate
import io.github.z0r3f.weather.core.forecast.domain.Location
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository
import io.github.z0r3f.weather.core.telegram.cqrs.AddLocationMessage
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.core.util.ErrorMessageFactory
import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class AddLocationHandlerTest {

    private val directGeocodingRepository: DirectGeocodingRepository = mock()
    private val newFavoriteEventPublisher: ApplicationEventPublisher<NewFavoriteEvent> = mock()
    private val newLocationEventPublisher: ApplicationEventPublisher<NewLocationEvent> = mock()
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: ActionHandler<AddLocationMessage, Unit>

    @BeforeEach
    internal fun setUp() {
        sut = AddLocationHandler(directGeocodingRepository, newFavoriteEventPublisher, newLocationEventPublisher, newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(newFavoriteEventPublisher, newLocationEventPublisher, newMessageEventPublisher)
    }

    @Test
    internal fun `test handle with no results returned`() {
        val chat = mock<Chat>()
        val cityName = "invalid_city_name"
        whenever(directGeocodingRepository.getCoordinatesByLocationName(cityName)).thenReturn(emptyList())

        sut.handle(AddLocationMessage(chat, cityName))

        verify(newMessageEventPublisher).publishEventAsync(MessageEvent(chat, ErrorMessageFactory.coordinateIsMissing(cityName)))
    }

    @Test
    internal fun `test handle with valid input`() {
        val chat = mock<Chat>()
        val locationName = "city_name"
        val country = "Spain"
        val coordinate = GeographicalCoordinate(name = locationName, latitude = 1.0, longitude = 1.0, country = country)
        whenever(directGeocodingRepository.getCoordinatesByLocationName(locationName)).thenReturn(listOf(coordinate))

        sut.handle(AddLocationMessage(chat, locationName))

        val expectedLocation = Location(name = locationName, latitude = 1.0, longitude = 1.0, country = country)
        verify(newFavoriteEventPublisher).publishEventAsync(NewFavoriteEvent(chat, expectedLocation))
        verify(newLocationEventPublisher).publishEventAsync(NewLocationEvent(chat, expectedLocation))
    }
}
