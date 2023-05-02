package io.github.z0r3f.weather.core.chat.cqrs

import io.github.z0r3f.weather.core.chat.domain.Chat

data class DeleteAlertMessage(val chat: Chat, val hourOfDayRaw: String) : BaseAlertMessage(hourOfDayRaw)
