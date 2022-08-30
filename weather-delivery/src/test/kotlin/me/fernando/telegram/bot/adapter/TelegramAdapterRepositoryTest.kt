package me.fernando.telegram.bot.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.*
import me.fernando.telegram.domain.BotCommand
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType.UNKNOWN
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.*
import org.skyscreamer.jsonassert.JSONCompare.compareJSON
import org.skyscreamer.jsonassert.JSONCompareMode.STRICT

internal class TelegramAdapterRepositoryTest {

    private val telegramApiClient: TelegramApiClient = mock()

    private lateinit var sut: TelegramAdapterRepository

    @BeforeEach
    fun setUpEach() {
        sut = TelegramAdapterRepository(telegramApiClient)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(telegramApiClient)
    }

    @Test
    fun send_message_without_callbacks() {
        sut.sendMessage(CHAT_ID, MESSAGE)
        verify(telegramApiClient).sendMessage(CHAT_ID, MESSAGE)
    }

    @Test
    fun send_message_with_callbacks() {
        sut.sendMessage(CHAT_ID, MESSAGE, CALLBACKS)

        verify(telegramApiClient).sendMessage(
            eq(CHAT_ID),
            eq(MESSAGE),
            ArgumentMatchers.argThat { compareReplyMarkupDtoWithJSON(REPLY_MARKUP, it) })
    }

    @Test
    fun get_the_commands_when_the_call_is_success() {
        whenever(telegramApiClient.getBotCommands()).thenReturn(COMMANDS_RAW_GETTER)

        val commands = sut.getAllTheCommands()

        assert(commands == COMMANDS)
        verify(telegramApiClient).getBotCommands()
    }

    @Test
    fun get_empty_set_when_the_call_is_unsuccessful() {
        whenever(telegramApiClient.getBotCommands()).thenReturn(COMMANDS_RAW_GETTER.copy(ok = false))

        val commands = sut.getAllTheCommands()

        assert(commands.isEmpty())
        verify(telegramApiClient).getBotCommands()
    }

    @Test
    fun set_all_the_commands() {
        sut.setAllTheCommands(COMMANDS)

        verify(telegramApiClient).setBotCommands(COMMANDS_RAW_SETTER)
    }

    private companion object {
        const val CHAT_ID = 1L
        const val MESSAGE = "Hello!!"
        val CALLBACKS = listOf(
            BotCallback(type = UNKNOWN, data = "Unknown 1"),
            BotCallback(type = UNKNOWN, data = "Unknown 2"),
        )
        val REPLY_MARKUP = ReplyMarkupDto(
            inlineKeyboard = listOf(
                listOf(
                    InlineKeyboardButtonDto(
                        text = "❌ Unknown", callbackData = "Unknown 1"
                    ), InlineKeyboardButtonDto(
                        text = "❌ Unknown", callbackData = "Unknown 2"
                    )
                )
            )
        )

        val COMMANDS_RAW_LIST = listOf(
            BotCommandDto(command = "start", description = "Start the bot"),
            BotCommandDto(command = "help", description = "Help"),
        )

        val COMMANDS_RAW_GETTER = BotGetCommandsResponseDto(
            ok = true, result = COMMANDS_RAW_LIST
        )

        val COMMANDS_RAW_SETTER = BotSetCommandsRequestDto(
            commands = COMMANDS_RAW_LIST
        )

        val COMMANDS = setOf(
            BotCommand(
                command = "start", description = "Start the bot"
            ), BotCommand(
                command = "help", description = "Help"
            )
        )

        fun compareReplyMarkupDtoWithJSON(expected: ReplyMarkupDto, actual: String?): Boolean {
            val mapper = ObjectMapper()
            mapper.propertyNamingStrategy = SNAKE_CASE
            val replyMarkup = mapper.writeValueAsString(expected)
            println(replyMarkup)

            return compareJSON(replyMarkup, actual, STRICT).passed()
        }
    }
}
