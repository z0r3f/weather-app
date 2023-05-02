package io.github.z0r3f.weather.core.forecast.service

import jakarta.inject.Singleton

@Singleton
class AddAlertOverviewService: OverviewService<Int> {
    override fun generateOverviewMessage(data: Int): String {
        return "Added alert at $data:00 every day"
    }
}
