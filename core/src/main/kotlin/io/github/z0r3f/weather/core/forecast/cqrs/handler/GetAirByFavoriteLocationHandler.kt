package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.github.z0r3f.weather.core.forecast.port.AirRepository
import jakarta.inject.Singleton

@Singleton
class GetAirByFavoriteLocationHandler(
    private val airRepository: AirRepository
) : ActionHandler<GetAirByFavoriteLocationMessage, AirData> {
    override fun handle(action: GetAirByFavoriteLocationMessage): AirData {
        return airRepository.getAirQuality(action.favoriteLocation.latitude!!, action.favoriteLocation.longitude!!)
    }
}
