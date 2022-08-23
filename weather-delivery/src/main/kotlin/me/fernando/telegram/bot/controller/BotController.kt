package me.fernando.telegram.bot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import jakarta.annotation.security.PermitAll
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.UpdateDto
import me.fernando.telegram.domain.BotCommand.FORECAST
import me.fernando.telegram.domain.BotCommand.HELP
import me.fernando.telegram.domain.BotCommandRequest
import me.fernando.util.sanitizeForTelegram
import me.fernando.weather.service.FormatForecast
import me.fernando.weather.service.FormatHelp
import me.fernando.weather.usecase.GetForecastByCityNameQry

@Controller("/bot")
@PermitAll
class BotController(
    private val telegramApiClient: TelegramApiClient,
    private val bus: UseCaseBus,
) {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    fun test() = "Hello World!"

    @Post
    fun incomingUpdate(update: UpdateDto) {
        processRequest(update)
    }

    private fun processRequest(update: UpdateDto) {

        println("Processing:\n${ObjectMapper().writeValueAsString(update)}")

        val message = update.message ?: update.editedMessage ?: throw IllegalArgumentException("Message not found")

        val chatId = message.chat.id

        runCatching {
            val text = message.text ?: throw IllegalArgumentException("Text not found")

            val botCommandRequest = choiceBotCommand(text)

            val response = when (botCommandRequest.command) {
                FORECAST -> {
                    val weatherData = bus(GetForecastByCityNameQry(botCommandRequest.arguments))
                    FormatForecast.overview(weatherData)
                }
                HELP -> FormatHelp.overview()
                else -> "Command not supported"
            }.trimIndent().sanitizeForTelegram()

            println("Response:\n${response}")

            telegramApiClient.sendMessage(chatId, response)

        }.onFailure { e ->
            val response = e.message ?: "Error processing your request"
            println("Response:\n${response}")
            telegramApiClient.sendMessage(chatId, response.sanitizeForTelegram())
        }
    }

    private fun choiceBotCommand(messageText: String): BotCommandRequest {
        return when {
            messageText.startsWith(FORECAST.command) -> BotCommandRequest(
                command = FORECAST,
                arguments = messageText.substringAfter(FORECAST.command).takeIf { it.isNotBlank() }
                    ?: throw IllegalArgumentException("Missing arguments")
            )
            messageText.startsWith(HELP.command) -> BotCommandRequest(
                command = HELP,
                arguments = messageText.substringAfter(HELP.command)
            )
            else -> throw IllegalArgumentException("Command not supported")
        }
    }
}
