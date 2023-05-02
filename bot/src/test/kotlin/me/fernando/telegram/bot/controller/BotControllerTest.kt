package me.fernando.telegram.bot.controller

import io.archimedesfw.cqrs.ActionBus
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.cqrs.AddAlertMessage
import me.fernando.chat.domain.Chat
import me.fernando.telegram.bot.dto.MessageDtoMother
import me.fernando.telegram.bot.dto.UpdateDtoMother
import me.fernando.telegram.cqrs.AddLocationMessage
import me.fernando.telegram.cqrs.DeleteMessage
import me.fernando.telegram.cqrs.HelpQueryMessage
import me.fernando.telegram.event.MessageEvent
import me.fernando.weather.cqrs.ForecastMessage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
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

    @Test
    fun `process #help`() {
        sut.incomingUpdate(HELP_COMMAND)

        verify(bus).dispatch(HelpQueryMessage(chat = CHAT))
    }

    @Test
    fun `process #forecast`() {
        sut.incomingUpdate(FORECAST_COMMAND)

        verify(bus).dispatch(ForecastMessage(chat = CHAT, cityName = ""))
    }

    @Test
    fun `process #addlocation`() {
        sut.incomingUpdate(ADD_LOCATION_COMMAND)

        verify(bus).dispatch(AddLocationMessage(chat = CHAT, cityName = " $CITY_SHANGHAI"))
    }

    @Test
    fun `process #dellocation`() {
        sut.incomingUpdate(DEL_LOCATION_COMMAND)

        verify(bus).dispatch(DeleteMessage(chat = CHAT, cityName = " $CITY_SAO_PAULO"))
    }

    @Test
    fun `process #addalert`() {
        sut.incomingUpdate(ADD_ALERT_COMMAND)

        verify(bus).dispatch(AddAlertMessage(chat = CHAT, hourOfDayRaw = " $ALERT_SEVEN"))
    }

    private companion object {
        const val CITY_SHANGHAI = "Shanghai"
        const val CITY_SAO_PAULO = "São Paulo"
        const val ALERT_SEVEN = "7"
        val CHAT = Chat(
            id = 1L,
            title = "title",
            username = "johnihyde",
        )
        val HELP_COMMAND = UpdateDtoMother().of(message = MessageDtoMother().of(text = "/help"))
        val FORECAST_COMMAND = UpdateDtoMother().of(message = MessageDtoMother().of(text = "/forecast"))
        val ADD_LOCATION_COMMAND = UpdateDtoMother().of(message = MessageDtoMother().of(text = "/addlocation $CITY_SHANGHAI"))
        val DEL_LOCATION_COMMAND = UpdateDtoMother().of(message = MessageDtoMother().of(text = "/dellocation $CITY_SAO_PAULO"))
        val ADD_ALERT_COMMAND = UpdateDtoMother().of(message = MessageDtoMother().of(text = "/addalert $ALERT_SEVEN"))
    }
}