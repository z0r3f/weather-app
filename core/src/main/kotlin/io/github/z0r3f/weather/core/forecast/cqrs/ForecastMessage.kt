package io.github.z0r3f.weather.core.forecast.cqrs

import io.archimedesfw.cqrs.QueryMessage
import io.github.z0r3f.weather.core.chat.domain.Chat

data class ForecastMessage(val chat: Chat, val cityName: String? = null, val hourOfDay: Int? = null) :
    QueryMessage<Unit>
