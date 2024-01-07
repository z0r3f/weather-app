package io.github.z0r3f.weather.core.forecast.port

import io.github.z0r3f.weather.core.forecast.domain.AirData

interface AirRepository {
    fun getAirQuality(latitude: Double, longitude: Double): AirData
}
