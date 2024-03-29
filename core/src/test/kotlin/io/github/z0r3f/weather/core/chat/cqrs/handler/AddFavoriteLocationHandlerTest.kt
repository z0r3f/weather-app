package io.github.z0r3f.weather.core.chat.cqrs.handler

import io.github.z0r3f.weather.core.chat.cqrs.AddFavoriteLocationMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import io.github.z0r3f.weather.core.forecast.domain.LocationMother
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class AddFavoriteLocationHandlerTest {

    private val chatRepository: ChatRepository = mock()

    private lateinit var sut: AddFavoriteLocationHandler

    @BeforeEach
    fun setUp() {
        sut = AddFavoriteLocationHandler(chatRepository)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(chatRepository)
    }

    @Test
    fun run_success() {
        sut.handle(AddFavoriteLocationMessage(CHAT, FAVORITE_LOCATION))

        verify(chatRepository).addFavoriteLocation(
            CHAT,
            FAVORITE_LOCATION
        )
    }

    private companion object {
        val CHAT = Chat(id = 1, title = "Chat")
        val FAVORITE_LOCATION = LocationMother().of().toFavoriteLocation()
    }
}
