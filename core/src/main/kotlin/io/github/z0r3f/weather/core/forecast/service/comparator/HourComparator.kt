package io.github.z0r3f.weather.core.forecast.service.comparator

import io.github.z0r3f.weather.core.forecast.domain.Forecast
import jakarta.inject.Singleton
import java.time.Duration
import java.time.LocalTime

@Singleton
class HourComparator {
    fun comparator(localTime: LocalTime): Comparator<Forecast> =
        compareBy {
            Duration.between(it.timeDataForecasted!!.toLocalTime(), localTime).abs().toMinutes()
        }
}
