package me.fernando.weather.api.dto

open class RainDtoMother {
    open fun of(
        `3h`: Double? = null
    ) = RainDto(
        `3h` = `3h` ?: 0.15
    )
}
