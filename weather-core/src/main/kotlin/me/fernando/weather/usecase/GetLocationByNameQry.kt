package me.fernando.weather.usecase

import io.archimedesfw.context.ServiceLocator
import io.archimedesfw.usecase.Query
import me.fernando.weather.domain.Location
import me.fernando.weather.port.DirectGeocodingRepository

@Deprecated(message = "Use DirectGeocodingRepository")
class GetLocationByNameQry(
    private val cityName: String,
    private val directGeocodingRepository: DirectGeocodingRepository = ServiceLocator.locate(),
): Query<Location>() {
    override fun run(): Location {
        return directGeocodingRepository.getCoordinatesByLocationName(cityName).first().toLocation()
    }
}
