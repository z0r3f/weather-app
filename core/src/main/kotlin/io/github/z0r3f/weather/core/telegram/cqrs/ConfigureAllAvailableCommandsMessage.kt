package io.github.z0r3f.weather.core.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage

data class ConfigureAllAvailableCommandsMessage(val dummy: Unit = Unit): CommandMessage<Unit>
