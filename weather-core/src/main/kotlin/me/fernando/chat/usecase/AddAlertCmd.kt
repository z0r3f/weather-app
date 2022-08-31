package me.fernando.chat.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.chat.port.ChatRepository
import me.fernando.telegram.usecase.SendMessageCmd
import me.fernando.weather.service.AddAlertOverviewService

class AddAlertCmd(
    private val chat: Chat,
    private val hourOfDayRaw: String,
    private val chatRepository: ChatRepository = locate(),
    private val addAlertOverviewService: AddAlertOverviewService = locate(),
) : Command<Unit>() {
    override fun run() {
        val hourOfDay = validateRequest()

        chatRepository.addAlert(chat, hourOfDay)

        val response = addAlertOverviewService.generateOverviewMessage(hourOfDay)

        run(SendMessageCmd(chat, response))
    }

    private fun validateRequest(): Int {
        try {
            val hourOfDay = Integer.parseInt(hourOfDayRaw.trim())
            if (hourOfDay < 0 || hourOfDay > 23) {
                throw IllegalArgumentException("Hour of day must be between 0 and 23")
            }
            return hourOfDay
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid hour of day: \"$hourOfDayRaw\". Should be an integer between 0 and 23")
        }
    }
}
