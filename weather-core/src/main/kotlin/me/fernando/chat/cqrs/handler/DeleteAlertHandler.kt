package me.fernando.chat.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.micronaut.context.event.ApplicationEventPublisher
import jakarta.inject.Singleton
import me.fernando.chat.cqrs.DeleteAlertMessage
import me.fernando.chat.event.RemoveAlertEvent
import me.fernando.chat.port.ChatRepository

@Singleton
class DeleteAlertHandler(
    private val chatRepository: ChatRepository,
    private val eventPublisher: ApplicationEventPublisher<RemoveAlertEvent>
) : ActionHandler<DeleteAlertMessage, Unit> {
    override fun handle(action: DeleteAlertMessage) {
        val hourOfDay = validateRequest(action.hourOfDayRaw)

        chatRepository.deleteAlert(action.chat, hourOfDay)

        eventPublisher.publishEventAsync(RemoveAlertEvent(action.chat, hourOfDay))
    }

    private fun validateRequest(hourOfDayRaw: String): Int {
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
