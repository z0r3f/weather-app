package me.fernando.telegram.cqrs.handler

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.cqrs.ActionHandler
import me.fernando.telegram.cqrs.HelpQueryMessage
import me.fernando.telegram.usecase.SendMessageCmd
import me.fernando.util.generateOverviewMessage
import me.fernando.weather.service.HelpOverviewService

class HelpHandler(
    private val helpOverviewService: HelpOverviewService = ServiceLocator.locate(),
): ActionHandler<HelpQueryMessage, Unit> {
    override fun handle(action: HelpQueryMessage) {
        val response = helpOverviewService.generateOverviewMessage()
        run(SendMessageCmd(chat, response))
    }
}
