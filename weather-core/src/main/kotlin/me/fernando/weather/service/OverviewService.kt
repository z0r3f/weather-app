package me.fernando.weather.service

interface OverviewService<T> {
    fun generateOverviewMessage(data: T): String
}

fun OverviewService<Unit>.generateOverviewMessage() = generateOverviewMessage(Unit)
