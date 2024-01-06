package io.github.z0r3f.weather.infrastructure.rest.telegram.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.archimedesfw.cqrs.ActionBus
import io.github.z0r3f.weather.core.chat.cqrs.AddAlertMessage
import io.github.z0r3f.weather.core.chat.cqrs.DeleteAlertMessage
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.cqrs.AirMessage
import io.github.z0r3f.weather.core.forecast.cqrs.CurrentMessage
import io.github.z0r3f.weather.core.forecast.cqrs.ForecastMessage
import io.github.z0r3f.weather.core.telegram.cqrs.*
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallback
import io.github.z0r3f.weather.core.telegram.domain.callback.BotCallbackType
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageRequest
import io.github.z0r3f.weather.core.telegram.domain.message.BotMessageType.*
import io.github.z0r3f.weather.core.telegram.event.MessageEvent
import io.github.z0r3f.weather.infrastructure.rest.telegram.dto.MessageDto
import io.github.z0r3f.weather.infrastructure.rest.telegram.dto.UpdateDto
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.annotation.security.PermitAll
import org.slf4j.LoggerFactory

@Controller("/bot")
@PermitAll
class BotController(
    private val bus: ActionBus,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent>,
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
                BotCallbackType.ADD -> bus.dispatch(AddLocationMessage(chat, botCallback.data))
                BotCallbackType.DELETE -> bus.dispatch(DeleteMessage(chat, botCallback.data))
                BotCallbackType.DELETE_ALERT -> bus.dispatch(DeleteAlertMessage(chat, botCallback.data))
                else -> bus.dispatch(CallbackUnknownMessage(chat))
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
                FORECAST -> bus.dispatch(ForecastMessage(chat, botCommandRequest.arguments))
                CURRENT -> bus.dispatch(CurrentMessage(chat, botCommandRequest.arguments))
                AIR -> bus.dispatch(AirMessage(chat, botCommandRequest.arguments))
                HELP -> bus.dispatch(HelpQueryMessage(chat))
                ADD_LOCATION -> bus.dispatch(AddLocationMessage(chat, botCommandRequest.arguments))
                DEL_LOCATION -> bus.dispatch(DeleteMessage(chat, botCommandRequest.arguments))
                ADD_ALERT -> bus.dispatch(AddAlertMessage(chat, botCommandRequest.arguments))
                // TODO Add to be able to show all favorite locations and send the forecast on them
                else -> bus.dispatch(NotSupportedQueryMessage(chat))
            }

        }.onFailure { e ->
            val response = e.message ?: "Error processing your request"

            newMessageEventPublisher.publishEventAsync(MessageEvent(chat, response))
        }
    }

    private fun choiceBotCommand(messageText: String): BotMessageRequest {
        return when {
            messageText.startsWith(FORECAST.command) -> BotMessageRequest(
                command = FORECAST,
                arguments = messageText.substringAfter(FORECAST.command)
            )
            messageText.startsWith(CURRENT.command) -> BotMessageRequest(
                command = CURRENT,
                arguments = messageText.substringAfter(CURRENT.command)
            )
            messageText.startsWith(AIR.command) -> BotMessageRequest(
                command = AIR,
                arguments = messageText.substringAfter(AIR.command)
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
