package me.fernando.telegram.bot.controller

import io.archimedesfw.usecase.UseCaseBus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.security.utils.SecurityService
import jakarta.annotation.security.PermitAll
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.UpdateDto
import me.fernando.weather.service.FormatWeather
import me.fernando.weather.usecase.GetForecastByCityNameQry

@Controller("/bot")
@PermitAll
class BotController(
    private val telegramApiClient: TelegramApiClient,
    private val bus: UseCaseBus,
    private val securityService: SecurityService,
) {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    fun test() = "Hello World!"

    @Post
    fun incomingUpdate(update: UpdateDto) {
        reply(update)
    }

    private fun reply(update: UpdateDto) {
        val chatId = update.message?.chat?.id
        val text = update.message?.text
        val weatherData = text?.let { bus(GetForecastByCityNameQry(it)) }
        val formatData = weatherData?.let { FormatWeather.overview(it) }
        val response = """
            $formatData
        """.trimIndent()
        chatId?.let { telegramApiClient.sendMessage(it, response) }
    }
}
