package me.fernando.weather.event

import me.fernando.chat.domain.Chat

data class RequestForecastFromFavoriteLocationsEvent(val chat: Chat)
