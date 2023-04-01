package me.fernando.weather.service.comparator

import jakarta.inject.Singleton
import me.fernando.weather.domain.Forecast
import java.time.Duration
import java.time.LocalTime

@Singleton
class HourComparator {
    fun comparator(localTime: LocalTime): Comparator<Forecast> =
        compareBy {
            Duration.between(it.timeDataForecasted!!.toLocalTime(), localTime).abs().toMinutes()
        }
}
