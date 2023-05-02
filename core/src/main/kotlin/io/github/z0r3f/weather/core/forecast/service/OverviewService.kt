package io.github.z0r3f.weather.core.forecast.service

interface OverviewService<T> {
    fun generateOverviewMessage(data: T): String
}
