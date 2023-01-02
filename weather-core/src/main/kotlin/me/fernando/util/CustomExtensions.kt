package me.fernando.util

import me.fernando.weather.service.OverviewService

// TODO Carry this to architecture module?

fun String.sanitizeForTelegram() = this.replace("([.\\-\\\\(\\\\)|])".toRegex(), "\\\\$1")

fun String.trimLeadingSpaces() = this.replace("(?m)(^ +)".toRegex(), "").trim()

fun OverviewService<Unit>.generateOverviewMessage() = generateOverviewMessage(Unit)

fun Any?.isNull() = this == null
