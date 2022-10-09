package me.fernando.telegram.cqrs.handler

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.cqrs.ActionHandler
import me.fernando.telegram.cqrs.SendMessage
import me.fernando.telegram.port.TelegramRepository
import me.fernando.util.sanitizeForTelegram
import org.slf4j.LoggerFactory

class SendMessageHandler(
    private val telegramRepository: TelegramRepository = ServiceLocator.locate(),
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
