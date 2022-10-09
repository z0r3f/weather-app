package me.fernando.telegram.bot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.archimedesfw.cqrs.ActionBus
import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.annotation.security.PermitAll
import me.fernando.chat.domain.Chat
import me.fernando.chat.usecase.AddAlertCmd
import me.fernando.telegram.bot.dto.MessageDto
import me.fernando.telegram.bot.dto.UpdateDto
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType
import me.fernando.telegram.domain.message.BotMessageRequest
import me.fernando.telegram.domain.message.BotMessageType.*
import me.fernando.telegram.usecase.*
import org.slf4j.LoggerFactory

@Controller("/bot")
@PermitAll
class BotController(
    private val bus: UseCaseBus,
    private val actionBus: ActionBus,
) {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    fun showAdvice() = "Look this: https://t.me/WeaFerBot"

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    fun incomingUpdate(@Body update: UpdateDto) {

        LOG.debug("Processing: ${ObjectMapper().writeValueAsString(update)}")

        if (update.hasCallbackQuery()) {
            processCallBack(update)
        } else {
            processMessage(update)
        }
    }

    private fun processCallBack(update: UpdateDto) {
        update.callbackQuery?.message?.let { message ->
            val chat = getChat(message)
            val botCallback = choiceBotCallback(update)

            when (botCallback.type) {
                BotCallbackType.ADD -> bus(AddLocationCmd(chat, botCallback.data))
                BotCallbackType.DELETE -> bus(DelLocationCmd(chat, botCallback.data))
                else -> bus(CallbackUnknownCmd(chat))
            }
        }
    }

    private fun choiceBotCallback(update: UpdateDto): BotCallback {
        return update.callbackQuery?.data?.split(":")?.let { data ->
            BotCallback(
                type = BotCallbackType.fromString(data[0]),
                data = data[1]
            )
        } ?: BotCallback(
            type = BotCallbackType.UNKNOWN,
            data = ""
        )
    }

    private fun processMessage(update: UpdateDto) {

        val message = update.message ?: update.editedMessage ?: throw IllegalArgumentException("Message not found")
        val chat = getChat(message)

        runCatching {
            val text = message.text ?: throw IllegalArgumentException("Hi!!")

            val botCommandRequest = choiceBotCommand(text)

            when (botCommandRequest.command) {
                FORECAST -> bus(ForecastCmd(chat, botCommandRequest.arguments))
                HELP -> bus(HelpCmd(chat))
                ADD_LOCATION -> bus(AddLocationCmd(chat, botCommandRequest.arguments))
                DEL_LOCATION -> bus(DelLocationCmd(chat, botCommandRequest.arguments))
                ADD_ALERT -> bus(AddAlertCmd(chat, botCommandRequest.arguments))
                // TODO Add to be able to show all favorite locations and send the forecast on them
                else -> bus(NotSupportedCmd(chat))
            }

        }.onFailure { e ->
            val response = e.message ?: "Error processing your request"

            bus(SendMessageCmd(chat, response))
        }
    }

    private fun choiceBotCommand(messageText: String): BotMessageRequest {
        return when {
            messageText.startsWith(FORECAST.command) -> BotMessageRequest(
                command = FORECAST,
                arguments = messageText.substringAfter(FORECAST.command)
            )
            messageText.startsWith(HELP.command) -> BotMessageRequest(
                command = HELP,
                arguments = messageText.substringAfter(HELP.command)
            )
            messageText.startsWith(ADD_LOCATION.command) -> BotMessageRequest(
                command = ADD_LOCATION,
                arguments = messageText.substringAfter(ADD_LOCATION.command).takeIf { it.isNotBlank() }
                    ?: throw IllegalArgumentException("Missing arguments")
            )
            messageText.startsWith(DEL_LOCATION.command) -> BotMessageRequest(
                command = DEL_LOCATION,
                arguments = messageText.substringAfter(DEL_LOCATION.command).takeIf { it.isNotBlank() }
                    ?: throw IllegalArgumentException("Missing arguments")
            )
            messageText.startsWith(ADD_ALERT.command) -> BotMessageRequest(
                command = ADD_ALERT,
                arguments = messageText.substringAfter(ADD_ALERT.command).takeIf { it.isNotBlank() }
                    ?: throw IllegalArgumentException("Missing arguments")
            )
            messageText.startsWith("/") -> throw IllegalArgumentException("Command not supported")
            else -> BotMessageRequest(
                command = FORECAST,
                arguments = messageText
            )
        }
    }

    //Refactor this, recovery from db
    private fun getChat(message: MessageDto): Chat {
        return Chat(
            id = message.chat.id,
            username = message.chat.username,
            title = message.chat.title,
        )
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(BotController::class.java)
    }
}
