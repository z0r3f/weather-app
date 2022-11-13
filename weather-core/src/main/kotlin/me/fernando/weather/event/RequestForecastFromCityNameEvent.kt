package me.fernando.weather.event

import me.fernando.chat.domain.Chat

data class RequestForecastFromCityNameEvent(val chat: Chat, val cityName: String)
