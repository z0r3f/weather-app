package me.fernando.weather.service

import jakarta.inject.Singleton

@Singleton
class RemoveAlertOverviewService: OverviewService<Int> {
    override fun generateOverviewMessage(data: Int): String {
        return "Deleted alert at $data:00 every day"
    }
}
