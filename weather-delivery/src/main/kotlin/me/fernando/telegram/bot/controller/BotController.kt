package me.fernando.telegram.bot.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import jakarta.annotation.security.PermitAll
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.UpdateDto

@Controller("/bot")
@PermitAll
class BotController(
    private val telegramApiClient: TelegramApiClient
) {

    @Post
    fun incomingUpdate(update: UpdateDto) {
        println(update)
        echo(update)
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    fun test() = "Hello World!"
    private fun echo(update: UpdateDto) {
        val chatId = update.message?.chat?.id
        val text = update.message?.text
        val response = """
            *$text*
        """.trimIndent()
        println("/sendMessage?chat_id=${chatId}&text=${text}")
        chatId?.let { telegramApiClient.sendMessage(it, response) }
    }
}
