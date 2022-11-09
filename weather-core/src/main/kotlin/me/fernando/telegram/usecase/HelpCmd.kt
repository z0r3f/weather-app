package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.RequestHelpDataEvent

class HelpCmd(
    private val chat: Chat,
    private val requestHelpDataEventPublisher: ApplicationEventPublisher<RequestHelpDataEvent> = locate(),
) : Command<Unit>() {
    override fun run() {
        requestHelpDataEventPublisher.publishEvent(RequestHelpDataEvent(chat))
    }
}
