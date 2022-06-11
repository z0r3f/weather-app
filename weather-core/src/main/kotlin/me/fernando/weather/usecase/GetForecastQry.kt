package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator.locate
import io.archimedesfw.usecase.Query
import me.fernando.weather.domain.Location
import me.fernando.weather.domain.WeatherData
import me.fernando.weather.repository.GetForecastRepository

class GetForecastQry(
    location: Location,
    getForecastRepository: GetForecastRepository = locate()
): Query<WeatherData>() {

    override fun run(): WeatherData {
        TODO("Not yet implemented")
    }
}
