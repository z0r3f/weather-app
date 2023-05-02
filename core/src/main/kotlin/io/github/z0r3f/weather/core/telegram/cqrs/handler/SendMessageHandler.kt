package io.github.z0r3f.weather.core.telegram.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.telegram.cqrs.SendMessage
import io.github.z0r3f.weather.core.telegram.port.TelegramRepository
import io.github.z0r3f.weather.core.util.sanitizeForTelegram
import org.slf4j.LoggerFactory

@Singleton
class SendMessageHandler(
    private val telegramRepository: TelegramRepository,
) : ActionHandler<SendMessage, Unit> {
    override fun handle(action: SendMessage) {
        if (action.message.isEmpty()) {
            LOG.error("The message is empty. Nothing to send")
            throw IllegalArgumentException("The message is empty. Nothing to send")
        }
        val saneMessage = action.message.sanitizeForTelegram()

        telegramRepository.sendMessage(action.chat.id, saneMessage, action.botCallbacks)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SendMessageHandler::class.java)
    }
}
