package me.fernando.telegram.bot.client

import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("https://api.telegram.org/bot\${telegram.token}")
interface TelegramApiClient {

    @Post("/setWebhook?url=\${telegram.webhook}")
    fun setWebhook()

//    @Post("/getWebhookInfo")
//    fun getWebhookInfo()

    @Post("/deleteWebhook")
    fun deleteWebhook()

    @Post("/sendMessage?chat_id=\${chatId}&text=\${text}")
    fun sendMessage(@PathVariable chatId: Long, @PathVariable text: String)
}
