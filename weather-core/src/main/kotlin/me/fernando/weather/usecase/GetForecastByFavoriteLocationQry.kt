package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Query
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.port.ForecastRepository

class GetForecastByFavoriteLocationQry(
    private val favoriteLocation: FavoriteLocation,
    private val forecastRepository: ForecastRepository = locate()
): Query<WeatherData>() {

    override fun run(): WeatherData {
        return forecastRepository.getForecast(favoriteLocation.latitude!!, favoriteLocation.longitude!!)
    }
}
