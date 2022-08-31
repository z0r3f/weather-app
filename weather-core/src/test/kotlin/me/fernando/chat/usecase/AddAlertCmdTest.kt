package me.fernando.chat.usecase

import io.archimedesfw.usecase.asSpiedRun
import io.archimedesfw.usecase.fakeRun
import me.fernando.chat.domain.Chat
import me.fernando.chat.port.ChatRepository
import me.fernando.telegram.usecase.SendMessageCmd
import me.fernando.weather.service.AddAlertOverviewService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class AddAlertCmdTest {

    private val chatRepository: ChatRepository = mock()
    private val addAlertOverviewService: AddAlertOverviewService = mock()

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(chatRepository, addAlertOverviewService)
    }

    @Test
    fun throw_exception_when_the_hour_requested_is_not_an_integer() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            AddAlertCmd(CHAT, "Not is valid number", chatRepository, addAlertOverviewService).fakeRun()
        }

        Assertions.assertTrue(exceptionThrown.message == "Invalid hour of day: \"Not is valid number\". Should be an integer between 0 and 23")
        verifyNoInteractions(chatRepository, addAlertOverviewService)
    }

    @Test
    fun throw_exception_when_the_hour_requested_is_not_an_integer_between_0_and_23() {
        val exceptionThrown = assertThrows(IllegalArgumentException::class.java) {
            AddAlertCmd(CHAT, "24", chatRepository, addAlertOverviewService).fakeRun()
        }

        Assertions.assertTrue(exceptionThrown.message == "Hour of day must be between 0 and 23")
        verifyNoInteractions(chatRepository, addAlertOverviewService)
    }

    @Test
    fun success_when_the_hour_requested_is_an_integer_between_0_and_23() {
        val sut = AddAlertCmd(CHAT, ALERT_MOMENT, chatRepository, addAlertOverviewService)
        whenever(addAlertOverviewService.generateOverviewMessage(Integer.parseInt(ALERT_MOMENT))).thenReturn(
            RESPONSE
        )
        sut.asSpiedRun(SendMessageCmd(CHAT, RESPONSE), Unit)

        sut.fakeRun()

        verify(chatRepository).addAlert(CHAT, ALERT_MOMENT_AS_INT)
        verify(addAlertOverviewService).generateOverviewMessage(ALERT_MOMENT_AS_INT)
    }

    private companion object {
        const val CHAT_ID = 123L
        val CHAT = Chat(id = CHAT_ID)
        const val ALERT_MOMENT = "23"
        const val ALERT_MOMENT_AS_INT = 23
        const val RESPONSE = "You will receive an alert at $ALERT_MOMENT hour"
    }
}
