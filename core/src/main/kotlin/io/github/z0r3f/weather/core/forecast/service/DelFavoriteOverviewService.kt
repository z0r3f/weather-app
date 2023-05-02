package io.github.z0r3f.weather.core.forecast.service

import jakarta.inject.Singleton
import io.github.z0r3f.weather.core.forecast.domain.Location

@Singleton
class DelFavoriteOverviewService: OverviewService<Location> {
    override fun generateOverviewMessage(data: Location): String {
        return "Deleted ${data.name} from favorites"
    }
}
