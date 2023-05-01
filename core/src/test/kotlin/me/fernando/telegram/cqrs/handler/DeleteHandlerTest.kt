package me.fernando.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.chat.port.ChatRepository
import me.fernando.telegram.cqrs.DeleteMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.util.ErrorMessageFactory
import me.fernando.weather.domain.Location
import me.fernando.weather.service.DelFavoriteOverviewService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class DeleteHandlerTest {

    private val chatRepository: ChatRepository = mock()
    private val delFavoriteOverviewService: DelFavoriteOverviewService = mock()
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: ActionHandler<DeleteMessage, Unit>

    @BeforeEach
    internal fun setUp() {
        sut = DeleteHandler(chatRepository, delFavoriteOverviewService, newMessageEventPublisher)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(chatRepository, delFavoriteOverviewService, newMessageEventPublisher)
    }

    @Test
    internal fun `should remove favorite location when it exists`() {
        val chat = mock<Chat>()
        val cityName = "testCity"
        val favoriteLocation = FavoriteLocation(chat = chat, name = cityName)
        val location = Location(name = cityName)
        val deleteMessage = "Deleted $cityName from favorites"

        whenever(chatRepository.getFavoriteLocations(chat)).thenReturn(listOf(favoriteLocation))
        whenever(delFavoriteOverviewService.generateOverviewMessage(location)).thenReturn(deleteMessage)

        sut.handle(DeleteMessage(chat, cityName))

        verify(chatRepository).getFavoriteLocations(chat)
        verify(chatRepository).removeFavoriteLocation(chat, favoriteLocation)
        verify(delFavoriteOverviewService).generateOverviewMessage(location)
        verify(newMessageEventPublisher).publishEventAsync(MessageEvent(chat, deleteMessage))
        verify(newMessageEventPublisher, never()).publishEventAsync(MessageEvent(chat, ErrorMessageFactory.notFoundFavoriteLocation()))
    }

    @Test
    internal fun `should publish error message when favorite location does not exist`() {
        val chat = mock<Chat>()
        val cityName = "testCity"
        val otherCityName = "otherTestCity"
        val favoriteLocation = FavoriteLocation(chat = chat, name = otherCityName)
        val location = Location(name = otherCityName)

        whenever(chatRepository.getFavoriteLocations(chat)).thenReturn(listOf(favoriteLocation))

        sut.handle(DeleteMessage(chat, cityName))

        verify(chatRepository).getFavoriteLocations(chat)
        verify(newMessageEventPublisher).publishEventAsync(MessageEvent(chat, ErrorMessageFactory.notFoundFavoriteLocation()))
    }
}
