package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.usecase.Command
import me.fernando.telegram.repository.TelegramRepository
import me.fernando.util.sanitizeForTelegram
import org.slf4j.LoggerFactory

class SendMessageCmd(
    private val chatId: Long,
    private val message: String,
    private val telegramApiClient: TelegramRepository = ServiceLocator.locate(),
): Command<Unit>() {
    override fun run() {
        if (message.isEmpty()) {
            LOG.error("The message is empty. Nothing to send")
            throw IllegalArgumentException("The message is empty. Nothing to send")
        }
        val saneMessage = message.sanitizeForTelegram()
        telegramApiClient.sendMessage(chatId, saneMessage)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SendMessageCmd::class.java)
    }
}
