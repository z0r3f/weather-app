package me.fernando.weather.service

import jakarta.inject.Singleton
import me.fernando.weather.domain.Location

@Singleton
class DelFavoriteOverviewService: OverviewService<Location> {
    override fun generateOverviewMessage(data: Location): String {
        return "Deleted ${data.name} from favorites"
    }
}
