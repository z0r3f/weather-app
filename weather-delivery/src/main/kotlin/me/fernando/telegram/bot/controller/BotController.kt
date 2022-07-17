package me.fernando.telegram.bot.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import me.fernando.telegram.bot.client.TelegramApiClient
import me.fernando.telegram.bot.dto.UpdateDto

@Controller("/bot")
class BotController(
    private val telegramApiClient: TelegramApiClient
) {

    @Post
    fun incomingUpdate(update: UpdateDto) {
        println(update)
        echo(update)
    }

    private fun echo(update: UpdateDto) {
        val chatId = update.message?.chat?.id
        val text = update.message?.text
        val response = """
            <b>$text</b>
        """.trimIndent()
        chatId?.let { telegramApiClient.sendMessage(it, response) }
    }
}
