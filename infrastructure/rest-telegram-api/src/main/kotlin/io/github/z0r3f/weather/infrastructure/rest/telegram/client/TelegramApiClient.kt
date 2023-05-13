package io.github.z0r3f.weather.infrastructure.rest.telegram.client

import io.github.z0r3f.weather.infrastructure.rest.telegram.dto.BotGetCommandsResponseDto
import io.github.z0r3f.weather.infrastructure.rest.telegram.dto.BotSetCommandsRequestDto
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("https://api.telegram.org/bot\${telegram.token}")
interface TelegramApiClient {

    @Post("/setWebhook")
    fun setWebhook(@Part("url") url: String)

//    @Post("/getWebhookInfo")
//    fun getWebhookInfo()

    @Post("/deleteWebhook")
    fun deleteWebhook()

    @Post("/sendMessage{?chat_id*}{&text*}{&reply_markup*}&parse_mode=MarkdownV2")
    fun sendMessage(@QueryValue chat_id: Long, @QueryValue text: String, @QueryValue reply_markup: String? = null)

    @Get("/getMyCommands")
    fun getBotCommands(): BotGetCommandsResponseDto

    @Post("/setMyCommands")
    @Header(name = "Content-Type", value = "application/json")
    fun setBotCommands(@Body("commands") commands: BotSetCommandsRequestDto)
}
