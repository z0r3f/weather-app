package me.fernando.weather.event

import me.fernando.chat.domain.Chat

@Deprecated("Use me.fernando.weather.event.RequestForecastEvent instead")
data class RequestForecastFromFavoriteLocationsEvent(val chat: Chat)
