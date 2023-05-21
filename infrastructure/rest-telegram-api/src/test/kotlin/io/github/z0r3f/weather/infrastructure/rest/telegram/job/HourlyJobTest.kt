package io.github.z0r3f.weather.infrastructure.rest.telegram.job

import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.cqrs.GetChatsMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.ForecastMessage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class HourlyJobTest {

    private val bus = mock<ActionBus>()

    private lateinit var sut: HourlyJob

    @BeforeEach
    internal fun setUp() {
        sut = HourlyJob(bus)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(bus)
    }

    @Test
    internal fun `execute should dispatch forecast message to all chats with alerts for the current hour`() {
        // Given
        val chats = listOf(CHAT_ONE, CHAT_TWO)
        whenever(bus.dispatch(any<GetChatsMessage>())).thenReturn(chats)

        // When
        sut.execute()

        // Then
        verify(bus).dispatch(any<GetChatsMessage>())
        verify(bus, times(2)).dispatch(any<ForecastMessage>())
    }

    private companion object {
        val CHAT_ONE = Chat(id = 1)
        val CHAT_TWO = Chat(id = 2)
    }
}
