package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.chat.usecase.AddFavoriteLocationCmd
import me.fernando.weather.service.AddFavoriteOverviewService
import me.fernando.weather.usecase.GetLocationByNameQry

class AddLocationCmd(
    private val chat: Chat,
    private val cityName: String,
    private val addFavoriteOverviewService: AddFavoriteOverviewService = locate(),
) : Command<Unit>() {
    override fun run() {
        val location = run(GetLocationByNameQry(cityName))

        run(
            AddFavoriteLocationCmd(
                chat = chat,
                favoriteLocation = location.toFavoriteLocation()
            )
        )

        val response = addFavoriteOverviewService.generateOverviewMessage(location)

        run(SendMessageCmd(chat, response))
    }
}
