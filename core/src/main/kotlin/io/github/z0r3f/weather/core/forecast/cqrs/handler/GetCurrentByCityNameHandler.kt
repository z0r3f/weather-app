package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByCityNameMessage
import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.core.forecast.port.CurrentRepository
import io.github.z0r3f.weather.core.forecast.port.DirectGeocodingRepository
import jakarta.inject.Singleton

@Singleton
class GetCurrentByCityNameHandler(
    private val directGeocodingRepository: DirectGeocodingRepository,
    private val currentRepository: CurrentRepository,
) : ActionHandler<GetCurrentByCityNameMessage, CurrentData> {

    override fun handle(action: GetCurrentByCityNameMessage): CurrentData {
        val cityName = action.cityName.trim()
        if (cityName.isEmpty()) {
            throw IllegalArgumentException("Invalid location")
        }

        val locations = directGeocodingRepository.getCoordinatesByLocationName(cityName)
        if (locations.isEmpty()) {
            throw IllegalArgumentException("Location not found")
        }

        val location = locations.first()
        val currentData = currentRepository.getCurrent(location.latitude, location.longitude)
        return currentData.copy(location = currentData.location?.copy(name = cityName))
    }
}
