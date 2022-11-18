package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.port.TelegramRepository
import me.fernando.util.sanitizeForTelegram
import org.slf4j.LoggerFactory

@Deprecated("Use me.fernando.telegram.cqrs.SendMessage instead")
class SendMessageCmd(
    private val chat: Chat,
    private val message: String,
    private val botCallbacks: List<BotCallback>? = null,
    private val telegramRepository: TelegramRepository = ServiceLocator.locate(),
) : Command<Unit>() {
    override fun run() {
        if (message.isEmpty()) {
            LOG.error("The message is empty. Nothing to send")
            throw IllegalArgumentException("The message is empty. Nothing to send")
        }
        val saneMessage = message.sanitizeForTelegram()

        telegramRepository.sendMessage(chat.id, saneMessage, botCallbacks)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SendMessageCmd::class.java)
    }
}
