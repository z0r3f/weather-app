package me.fernando.weather.cqrs

import io.archimedesfw.cqrs.QueryMessage
import me.fernando.chat.domain.Chat

data class ForecastMessage(val chat: Chat, val cityName: String? = null, val hourOfDay: Int? = null) :
    QueryMessage<Unit>
