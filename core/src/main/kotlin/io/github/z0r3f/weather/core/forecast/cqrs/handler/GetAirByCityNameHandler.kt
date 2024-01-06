package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByCityNameMessage
import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.github.z0r3f.weather.core.forecast.port.AirRepository
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository
import jakarta.inject.Singleton

@Singleton
class GetAirByCityNameHandler(
    private val directGeocodingRepository: DirectGeocodingRepository,
    private val airRepository: AirRepository,
) : ActionHandler<GetAirByCityNameMessage, AirData> {

    override fun handle(action: GetAirByCityNameMessage): AirData {
        val cityName = action.cityName.trim()
        if (cityName.isEmpty()) {
            throw IllegalArgumentException("Invalid location")
        }

        val locations = directGeocodingRepository.getCoordinatesByLocationName(cityName)
        if (locations.isEmpty()) {
            throw IllegalArgumentException("Location not found")
        }

        val location = locations.first()
        val airData = airRepository.getAirQuality(location.latitude, location.longitude)
        return airData.copy(location = airData.location?.copy(name = cityName))
    }
}
