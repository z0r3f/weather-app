package me.fernando.weather.service

import jakarta.inject.Singleton

@Singleton
class AddAlertOverviewService: OverviewService<Int> {
    override fun generateOverviewMessage(hourOfDay: Int): String {
        return "Added alert at $hourOfDay:00 UTC every day"
    }
}
