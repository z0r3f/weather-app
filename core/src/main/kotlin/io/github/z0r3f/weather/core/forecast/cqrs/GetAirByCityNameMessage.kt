package io.github.z0r3f.weather.core.forecast.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.forecast.domain.AirData

data class GetAirByCityNameMessage(val cityName: String) : QueryMessage<AirData>
