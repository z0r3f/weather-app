package me.fernando.chat.event

import me.fernando.chat.domain.Chat
import me.fernando.weather.domain.Location

data class NewLocationEvent(val chat: Chat, val location: Location)
