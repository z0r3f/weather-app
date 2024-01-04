package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.archimedesfw.cqrs.ActionHandler
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.core.forecast.port.CurrentRepository
import jakarta.inject.Singleton

@Singleton
class GetCurrentByFavoriteLocationHandler(
    private val currentRepository: CurrentRepository
) : ActionHandler<GetCurrentByFavoriteLocationMessage, CurrentData> {
    override fun handle(action: GetCurrentByFavoriteLocationMessage): CurrentData {
        return currentRepository.getCurrent(action.favoriteLocation.latitude!!, action.favoriteLocation.longitude!!)
    }
}
