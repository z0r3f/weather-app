package io.github.z0r3f.weather.core.forecast.event

import io.github.z0r3f.weather.core.chat.domain.Chat

data class RequestAirEvent(val chat: Chat, val cityName: String? = null)
