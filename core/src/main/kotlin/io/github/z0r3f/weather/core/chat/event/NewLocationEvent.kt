package io.github.z0r3f.weather.core.chat.event

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.forecast.domain.Location

data class NewLocationEvent(val chat: Chat, val location: Location)
