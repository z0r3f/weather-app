package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.telegram.domain.callback.BotCallback
import me.fernando.telegram.domain.callback.BotCallbackType
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.service.ForecastOverviewService
import me.fernando.weather.usecase.GetForecastByCityNameQry
import me.fernando.weather.usecase.GetForecastByFavoriteLocationsQry

class ForecastCmd(
    private val chat: Chat,
    private val cityName: String? = null,
    private val forecastOverviewService: ForecastOverviewService = locate(),
) : Command<Unit>() {
    override fun run() {
        val weatherData = if (!cityName.isNullOrEmpty()) {
            handleWithCityName(cityName)
        } else {
            handleFromFavoriteLocations(chat)
        }

        val responsesWithCallback = weatherData.map {
            Pair(
                forecastOverviewService.generateOverviewMessage(it),
                if (!cityName.isNullOrEmpty()) {
                    addOptionAddFavoriteLocation(it.location!!.name!!)
                } else {
                    addOptionDeleteFavoriteLocation(it.location!!.name!!)
                }
            )
        }

        responsesWithCallback.forEach { (response, botCallback) ->
            run(SendMessageCmd(chat, response, listOf(botCallback)))
        }
    }

    private fun handleWithCityName(cityName: String): List<WeatherData> {
        return listOf(run(GetForecastByCityNameQry(cityName)))
    }

    private fun handleFromFavoriteLocations(chat: Chat): List<WeatherData> {
        return run(GetForecastByFavoriteLocationsQry(chat))
    }

    private fun addOptionAddFavoriteLocation(cityName: String) = BotCallback(
        type = BotCallbackType.ADD,
        data = "${BotCallbackType.ADD.name}:$cityName"
    )

    private fun addOptionDeleteFavoriteLocation(cityName: String) = BotCallback(
        type = BotCallbackType.DELETE,
        data = "${BotCallbackType.DELETE.name}:$cityName"
    )
}
