package me.fernando.telegram.bot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import jakarta.annotation.security.PermitAll
import me.fernando.chat.domain.Chat
import me.fernando.telegram.bot.dto.MessageDto
import me.fernando.telegram.bot.dto.UpdateDto
import me.fernando.telegram.domain.BotCommandRequest
import me.fernando.telegram.domain.BotCommandType.*
import me.fernando.telegram.usecase.*
import org.slf4j.LoggerFactory

@Controller("/bot")
@PermitAll
class BotController(
    private val bus: UseCaseBus,
) {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    fun showAdvice() = "Look this: https://t.me/WeaFerBot"

    @Post
    fun incomingUpdate(update: UpdateDto) {
        processRequest(update)
    }

    private fun processRequest(update: UpdateDto) {

        LOG.debug("Processing:\n${ObjectMapper().writeValueAsString(update)}")

        val message = update.message ?: update.editedMessage ?: throw IllegalArgumentException("Message not found")
        val chat = getChat(message)

        runCatching {
            val text = message.text ?: throw IllegalArgumentException("Text not found")

            val botCommandRequest = choiceBotCommand(text)

            when (botCommandRequest.command) {
                FORECAST -> bus(ForecastCmd(chat, botCommandRequest.arguments))
                HELP -> bus(HelpCmd(chat))
                ADD_LOCATION -> bus(AddLocationCmd(chat, botCommandRequest.arguments))
                DEL_LOCATION -> bus(DelLocationCmd(chat, botCommandRequest.arguments))
                else -> bus(NotSupportedCmd(chat))
            }

        }.onFailure { e ->
            val response = e.message ?: "Error processing your request"

            bus(SendMessageCmd(chat, response))
        }
    }

    private fun choiceBotCommand(messageText: String): BotCommandRequest {
        return when {
            messageText.startsWith(FORECAST.command) -> BotCommandRequest(
                command = FORECAST,
                arguments = messageText.substringAfter(FORECAST.command)
            )
            messageText.startsWith(HELP.command) -> BotCommandRequest(
                command = HELP,
                arguments = messageText.substringAfter(HELP.command)
            )
            messageText.startsWith(ADD_LOCATION.command) -> BotCommandRequest(
                command = ADD_LOCATION,
                arguments = messageText.substringAfter(ADD_LOCATION.command).takeIf { it.isNotBlank() }
                    ?: throw IllegalArgumentException("Missing arguments")
            )
            messageText.startsWith(DEL_LOCATION.command) -> BotCommandRequest(
                command = DEL_LOCATION,
                arguments = messageText.substringAfter(DEL_LOCATION.command).takeIf { it.isNotBlank() }
                    ?: throw IllegalArgumentException("Missing arguments")
            )
            messageText.startsWith("/") -> throw IllegalArgumentException("Command not supported")
            else -> BotCommandRequest(
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
