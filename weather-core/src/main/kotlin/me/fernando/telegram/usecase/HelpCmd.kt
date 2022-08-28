package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.util.generateOverviewMessage
import me.fernando.weather.service.HelpOverviewService

class HelpCmd(
    private val chat: Chat,
    private val helpOverviewService: HelpOverviewService = locate(),
) : Command<Unit>() {
    override fun run() {
        val response = helpOverviewService.generateOverviewMessage()
        run(SendMessageCmd(chat, response))
    }
}
