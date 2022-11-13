package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.telegram.event.MessageEvent

class CallbackUnknownCmd(
    private val chat: Chat,
    private val newMessageEventPublisher: ApplicationEventPublisher<MessageEvent> = locate(),
) : Command<Unit>() {
    override fun run() {
        newMessageEventPublisher.publishEvent(MessageEvent(chat, "Callback unknown"))
    }
}
