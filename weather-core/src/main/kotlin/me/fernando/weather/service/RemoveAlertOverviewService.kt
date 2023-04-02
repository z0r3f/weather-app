package me.fernando.weather.service

import jakarta.inject.Singleton

@Singleton
class RemoveAlertOverviewService: OverviewService<Int> {
    override fun generateOverviewMessage(hourOfDay: Int): String {
        return "Deleted alert at $hourOfDay:00 every day"
    }
}
