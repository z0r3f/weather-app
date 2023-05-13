package io.github.z0r3f.weather.core.forecast.service

import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.forecast.domain.Location

@Singleton
class AddFavoriteOverviewService: OverviewService<Location> {
    override fun generateOverviewMessage(data: Location): String {
        return "Added ${data.name} (${data.latitude}, ${data.longitude}) to favorites"
    }
}
