package io.github.z0r3f.weather.core.forecast.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.forecast.domain.CurrentData

data class GetCurrentByCityNameMessage(val cityName: String) : QueryMessage<CurrentData>
