package io.github.z0r3f.weather.core.forecast.port

import io.github.z0r3f.weather.core.forecast.domain.CurrentData

interface CurrentRepository {
    fun getCurrent(latitude: Double, longitude: Double): CurrentData
}
