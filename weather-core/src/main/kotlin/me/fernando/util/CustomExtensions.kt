package me.fernando.util

import me.fernando.weather.service.OverviewService

fun String.sanitizeForTelegram() = this.replace("([.\\-\\\\(\\\\)|])".toRegex(), "\\\\$1")

fun String.trimLeadingSpaces() = this.replace("(?m)(^ +)".toRegex(), "").trim()

fun OverviewService<Unit>.generateOverviewMessage() = generateOverviewMessage(Unit)
