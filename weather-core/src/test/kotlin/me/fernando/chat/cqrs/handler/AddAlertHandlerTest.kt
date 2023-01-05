package me.fernando.chat.cqrs.handler

import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.cqrs.AddAlertMessage
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.NewAlertEvent
import me.fernando.chat.port.ChatRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class AddAlertHandlerTest {
    private val chatRepository: ChatRepository = mock()
    private val eventPublisher: ApplicationEventPublisher<NewAlertEvent> = mock()

    private lateinit var sut: AddAlertHandler

    @BeforeEach
    internal fun setUp() {
        sut = AddAlertHandler(chatRepository, eventPublisher)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(chatRepository, eventPublisher)
    }

    @Test
    fun throw_exception_when_the_hour_requested_is_not_an_integer() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            sut.handle(AddAlertMessage(CHAT, "Not is valid number"))
        }

        assertTrue(exceptionThrown.message == "Invalid hour of day: \"Not is valid number\". Should be an integer between 0 and 23")
        verifyNoInteractions(chatRepository, eventPublisher)
    }

    @Test
    fun throw_exception_when_the_hour_requested_is_not_an_integer_between_0_and_23() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            sut.handle(AddAlertMessage(CHAT, "24"))
        }

        assertTrue(exceptionThrown.message == "Hour of day must be between 0 and 23")
        verifyNoInteractions(chatRepository, eventPublisher)
    }

    @Test
    fun success_when_the_hour_requested_is_an_integer_between_0_and_23() {
        sut.handle(AddAlertMessage(CHAT, ALERT_MOMENT))

        verify(chatRepository).addAlert(CHAT, ALERT_MOMENT_AS_INT)
        verify(eventPublisher).publishEventAsync(NewAlertEvent(CHAT, ALERT_MOMENT_AS_INT))
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat(id = CHAT_ID)
        const val ALERT_MOMENT = "23"
        const val ALERT_MOMENT_AS_INT = 23
    }
}
