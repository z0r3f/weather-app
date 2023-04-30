package me.fernando.telegram.cqrs

import io.archimedesfw.cqrs.CommandMessage

data class ConfigureAllAvailableCommandsMessage(val dummy: Unit = Unit): CommandMessage<Unit>
