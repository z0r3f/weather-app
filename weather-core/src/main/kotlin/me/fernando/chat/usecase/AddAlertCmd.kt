package me.fernando.chat.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import io.micronaut.context.event.ApplicationEventPublisher
import me.fernando.chat.domain.Chat
import me.fernando.chat.event.NewAlertEvent
import me.fernando.chat.port.ChatRepository

class AddAlertCmd(
    private val chat: Chat,
    private val hourOfDayRaw: String,
    private val chatRepository: ChatRepository = locate(),
    private val eventPublisher: ApplicationEventPublisher<NewAlertEvent> = locate(),
) : Command<Unit>() {
    override fun run() {
        val hourOfDay = validateRequest()

        chatRepository.addAlert(chat, hourOfDay)

        eventPublisher.publishEvent(NewAlertEvent(chat, hourOfDay))
    }

    private fun validateRequest(): Int {
        try {
            val hourOfDay = Integer.parseInt(hourOfDayRaw.trim())
            if (hourOfDay in 0..23) {
                return hourOfDay
            }
            throw IllegalArgumentException("Hour of day must be between 0 and 23")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid hour of day: \"$hourOfDayRaw\". Should be an integer between 0 and 23")
        }
    }
}
