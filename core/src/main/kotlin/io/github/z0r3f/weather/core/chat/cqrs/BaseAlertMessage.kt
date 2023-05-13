package io.github.z0r3f.weather.core.chat.cqrs

import io.archimedesfw.cqrs.CommandMessage
import io.github.z0r3f.weather.core.util.ErrorMessageFactory

abstract class BaseAlertMessage(hourOfDayRaw: String): CommandMessage<Unit> {
    val hourOfDay = validateHourOfDay(hourOfDayRaw)

    private fun validateHourOfDay(hourOfDayRaw: String): Int {
        val hourOfDay = hourOfDayRaw.trim().toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessageFactory.invalidHourOfDay(hourOfDayRaw))

        return if (hourOfDay in 0..23) {
            hourOfDay
        } else {
            throw IllegalArgumentException(ErrorMessageFactory.hourOfDayShouldBeBetweenValidRange())
        }
    }
}
