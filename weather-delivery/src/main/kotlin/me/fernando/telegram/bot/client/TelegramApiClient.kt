package me.fernando.telegram.bot.client

import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import me.fernando.telegram.bot.dto.BotGetCommandsResponseDto
import me.fernando.telegram.bot.dto.BotSetCommandsRequestDto

@Client("https://api.telegram.org/bot\${telegram.token}")
interface TelegramApiClient {

    @Post("/setWebhook")
    fun setWebhook(@Part("url") url: String)

//    @Post("/getWebhookInfo")
//    fun getWebhookInfo()

    @Post("/deleteWebhook")
    fun deleteWebhook()

    @Post("/sendMessage{?chat_id*}{&text*}&parse_mode=MarkdownV2")
    fun sendMessage(@QueryValue chat_id: Long, @QueryValue text: String)

    @Get("/getMyCommands")
    fun getBotCommands(): BotGetCommandsResponseDto

    @Post("/setMyCommands")
    @Header(name = "Content-Type", value = "application/json")
    fun setBotCommands(@Body("commands") commands: BotSetCommandsRequestDto)
}
