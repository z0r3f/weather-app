package me.fernando.weather.service

import jakarta.inject.Singleton
import me.fernando.weather.domain.Location

@Singleton
class AddFavoriteOverviewService: OverviewService<Location> {
    override fun generateOverviewMessage(data: Location): String {
        return "Added ${data.name} (${data.latitude}, ${data.longitude}) to favorites"
    }
}
