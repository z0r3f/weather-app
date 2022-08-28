package me.fernando.telegram.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Command
import me.fernando.chat.domain.Chat
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.service.ForecastOverviewService
import me.fernando.weather.usecase.GetForecastByCityNameQry
import me.fernando.weather.usecase.GetForecastByFavoriteLocationsQry

class ForecastCmd(
    private val chat: Chat,
    private val cityName: String? = null,
    private val forecastOverviewService: ForecastOverviewService = locate()
) : Command<Unit>() {
    override fun run() {
        val weatherData = if (!cityName.isNullOrEmpty()) {
            handleWithCityName(cityName)
        } else {
            handleFromFavoriteLocations(chat)
        }

        val responses = weatherData.map { weatherData ->
            forecastOverviewService.generateOverviewMessage(weatherData)
        }

        responses.forEach { response ->
            run(SendMessageCmd(chat, response))
        }
    }

    private fun handleWithCityName(cityName: String): List<WeatherData> {
        return listOf(run(GetForecastByCityNameQry(cityName)))
    }

    private fun handleFromFavoriteLocations(chat: Chat): List<WeatherData> {
        return run(GetForecastByFavoriteLocationsQry(chat))
    }
}
