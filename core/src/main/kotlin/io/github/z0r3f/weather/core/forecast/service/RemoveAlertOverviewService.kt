package io.github.z0r3f.weather.core.forecast.service

import jakarta.inject.Singleton

@Singleton
class RemoveAlertOverviewService: OverviewService<Int> {
    override fun generateOverviewMessage(data: Int): String {
        return "Deleted alert at $data:00 every day"
    }
}
