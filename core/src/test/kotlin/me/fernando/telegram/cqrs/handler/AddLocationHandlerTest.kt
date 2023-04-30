package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.NewFavoriteEvent
import me.fernando.chat.event.NewLocationEvent
import me.fernando.telegram.cqrs.AddLocationMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory
import me.fernando.weather.domain.GeographicalCoordinate
import me.fernando.weather.domain.Location
import me.fernando.weather.port.DirectGeocodingRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
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
        MockitoAnnotations.openMocks(this)
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
