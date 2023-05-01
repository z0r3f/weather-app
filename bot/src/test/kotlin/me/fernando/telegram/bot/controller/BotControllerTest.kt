package me.fernando.telegram.bot.controller

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.telegram.event.MessageEvent
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions

class BotControllerTest {

    private val bus: ActionBus = mock()
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = mock()

    private lateinit var sut: BotController

    @BeforeEach
    fun setUp() {
        sut = BotController(bus, newMessageEventPublisher)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(bus, newMessageEventPublisher)
    }

    @Test
    fun showAdvice() {
        assertEquals("Look this: https://t.me/WeaFerBot", sut.showAdvice())
    }
}
