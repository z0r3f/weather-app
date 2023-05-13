package io.github.z0r3f.weather.core.chat.event

import io.github.z0r3f.weather.core.chat.domain.Chat

data class NewAlertEvent(val chat: Chat, val hourOfDay: Int)
