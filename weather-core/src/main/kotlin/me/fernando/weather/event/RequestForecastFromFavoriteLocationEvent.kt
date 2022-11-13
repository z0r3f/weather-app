package me.fernando.weather.event

import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation

data class RequestForecastFromFavoriteLocationEvent(val chat: Chat, val favoriteLocation: FavoriteLocation)
