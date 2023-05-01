package me.fernando.telegram.bot.job

import io.archimedesfw.cqrs.ActionBus
import me.fernando.chat.db.adapter.ChatAdapterRepository
import me.fernando.chat.domain.Chat
import me.fernando.weather.cqrs.ForecastMessage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class HourlyJobTest {

    private val chatAdapterRepository = mock<ChatAdapterRepository>()
    private val bus = mock<ActionBus>()

    private lateinit var sut: HourlyJob

    @BeforeEach
    internal fun setUp() {
        sut = HourlyJob(chatAdapterRepository, bus)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(chatAdapterRepository, bus)
    }

    @Test
    internal fun `execute should dispatch forecast message to all chats with alerts for the current hour`() {
        // Given
        val chats = listOf(CHAT_ONE, CHAT_TWO)
        whenever(chatAdapterRepository.getAlerts(any())).thenReturn(chats)

        // When
        sut.execute()

        // Then
        verify(chatAdapterRepository).getAlerts(any())
        verify(bus, times(2)).dispatch(any<ForecastMessage>())
    }

    private companion object {
        val CHAT_ONE = Chat(id = 1)
        val CHAT_TWO = Chat(id = 2)
    }
}
