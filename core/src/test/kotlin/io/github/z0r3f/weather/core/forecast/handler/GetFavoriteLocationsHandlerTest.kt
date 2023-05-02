package io.github.z0r3f.weather.core.forecast.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import io.github.z0r3f.weather.core.forecast.cqrs.GetFavoriteLocationsMessage
import io.github.z0r3f.weather.core.forecast.cqrs.handler.GetFavoriteLocationsHandler
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class GetFavoriteLocationsHandlerTest {

    private val chatRepository: ChatRepository = mock()

    private lateinit var sut: GetFavoriteLocationsHandler

    @BeforeEach
    internal fun setUp() {
        sut = GetFavoriteLocationsHandler(chatRepository)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(chatRepository)
    }

    @Test
    internal fun `handle should return expected favorite locations`() {
        val chat = mock<Chat>()
        val favoriteLocations = listOf(
            FavoriteLocation(chat = chat, name = "Tokyo", latitude = -35.6895, longitude = 139.6917, country = "Japan"),
            FavoriteLocation(chat = chat, name = "Delhi", latitude = -28.7041, longitude = 77.1025, country = "India"),
            FavoriteLocation(chat = chat, name = "Shanghai", latitude = -31.2304, longitude = 121.4737, country = "China"),
            FavoriteLocation(chat = chat, name = "São Paulo", latitude = -23.5505, longitude = -46.6333, country = "Brazil"),
            FavoriteLocation(chat = chat, name = "Mexico City", latitude = -19.4326, longitude = -99.1332, country = "Mexico"),
        )
        val action = GetFavoriteLocationsMessage(chat)
        whenever(chatRepository.getFavoriteLocations(chat)).thenReturn(favoriteLocations)

        val actual = sut.handle(action)

        assertEquals(favoriteLocations, actual)
        verify(chatRepository).getFavoriteLocations(chat)
    }
}
