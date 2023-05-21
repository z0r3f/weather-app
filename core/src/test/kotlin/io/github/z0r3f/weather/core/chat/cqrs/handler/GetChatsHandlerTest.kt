package io.github.z0r3f.weather.core.chat.cqrs.handler

import io.github.z0r3f.weather.core.chat.cqrs.GetChatsMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class GetChatsHandlerTest {
    private val chatRepository: ChatRepository = mock()

    private lateinit var sut: GetChatsHandler

    @BeforeEach
    internal fun setUp() {
        sut = GetChatsHandler(chatRepository)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(chatRepository)
    }

    @Test
    fun `should return an empty list if there is no chat by selected hour`() {
        val hourOfDay = 5
        whenever(chatRepository.getAlerts(hourOfDay = hourOfDay)).thenReturn(emptyList())

        val actual = sut.handle(GetChatsMessage(hourOfDay = hourOfDay))

        assert(actual.isEmpty())
        verify(chatRepository).getAlerts(hourOfDay = hourOfDay)
    }

    @Test
    fun `should return a list of chats if there are any chats for the selected hour`() {
        val hourOfDay = 9
        val expected = LIST_OF_CHATS
        whenever(chatRepository.getAlerts(hourOfDay = hourOfDay)).thenReturn(LIST_OF_CHATS)

        val actual = sut.handle(GetChatsMessage(hourOfDay = hourOfDay))

        assertEquals(expected, actual)
        verify(chatRepository).getAlerts(hourOfDay = hourOfDay)
    }

    private companion object {
        val LIST_OF_CHATS = listOf(
            Chat(id = 1),
            Chat(id = 2),
        )
    }
}
