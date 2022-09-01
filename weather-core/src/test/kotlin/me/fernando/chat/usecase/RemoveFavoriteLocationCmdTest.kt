package me.fernando.chat.usecase

import io.archimedesfw.usecase.fakeRun
import me.fernando.chat.domain.Chat
import me.fernando.chat.port.ChatRepository
import me.fernando.weather.domain.LocationMother
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class RemoveFavoriteLocationCmdTest {

    private val chatRepository: ChatRepository = mock()

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(chatRepository)
    }

    @Test
    fun run_success() {
        RemoveFavoriteLocationCmd(CHAT, FAVORITE_LOCATION, chatRepository).fakeRun()

        verify(chatRepository).removeFavoriteLocation(CHAT, FAVORITE_LOCATION)
    }

    private companion object {
        val CHAT = Chat(id = 1, title = "Chat")
        val FAVORITE_LOCATION = LocationMother().of().toFavoriteLocation()
    }
}
