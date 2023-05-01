package me.fernando.chat.cqrs.handler

import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.cqrs.DeleteAlertMessage
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.RemoveAlertEvent
import me.fernando.chat.port.ChatRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

class DeleteAlertHandlerTest {
    private val chatRepository: ChatRepository = mock()
    private val eventPublisher: ApplicationEventPublisher<RemoveAlertEvent> = mock()

    private lateinit var sut: DeleteAlertHandler

    @BeforeEach
    internal fun setUp() {
        sut = DeleteAlertHandler(chatRepository, eventPublisher)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(chatRepository, eventPublisher)
    }

    @Test
    fun throw_exception_when_the_hour_requested_is_not_an_integer() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            sut.handle(DeleteAlertMessage(CHAT, "Not is valid number"))
        }

        assertEquals(
            "Invalid hour of day: \"Not is valid number\". Should be an integer between 0 and 23",
            exceptionThrown.message
        )
        verifyNoInteractions(chatRepository, eventPublisher)
    }

    @Test
    fun throw_exception_when_the_hour_requested_is_not_an_integer_between_0_and_23() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            sut.handle(DeleteAlertMessage(CHAT, "24"))
        }

        assertEquals(
            "Hour of day must be between 0 and 23",
            exceptionThrown.message
        )
        verifyNoInteractions(chatRepository, eventPublisher)
    }

    @Test
    fun success_when_the_hour_requested_is_an_integer_between_0_and_23() {
        sut.handle(DeleteAlertMessage(CHAT, ALERT_MOMENT))

        verify(chatRepository).deleteAlert(CHAT, ALERT_MOMENT_AS_INT)
        verify(eventPublisher).publishEventAsync(RemoveAlertEvent(CHAT, ALERT_MOMENT_AS_INT))
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat(id = CHAT_ID)
        const val ALERT_MOMENT = "23"
        const val ALERT_MOMENT_AS_INT = 23
    }
}

