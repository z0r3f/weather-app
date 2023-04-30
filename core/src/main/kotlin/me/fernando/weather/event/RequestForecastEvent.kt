package me.fernando.weather.event

import me.fernando.chat.domain.Chat

data class RequestForecastEvent(val chat: Chat, val cityName: String? = null, val hourOfDay: Int? = null)
