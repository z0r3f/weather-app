package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Query
import me.fernando.chat.domain.Chat
import me.fernando.chat.port.ChatRepository
import me.fernando.weather.domain.WeatherData

class GetForecastByFavoriteLocationsQry(
    private val chat: Chat,
    private val chatRepository: ChatRepository = locate()
): Query<List<WeatherData>>() {

    override fun run(): List<WeatherData> {
        val listFavoriteLocation = chatRepository.getFavoriteLocations(chat)

        if (listFavoriteLocation.isEmpty()) {
            throw Exception("No favorite locations found")
        }

        return listFavoriteLocation.map {
            restoreTheOriginalCityName(
                run(GetForecastByLocationQry(it.toLocation())),
                it.name!!
            )
        }
    }

    private fun restoreTheOriginalCityName(weatherData: WeatherData, cityName: String): WeatherData {
        return weatherData.copy(location = weatherData.location?.copy(name = cityName))
    }
}
