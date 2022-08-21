package me.fernando.weather.api.dto

open class SnowDtoMother {
    open fun of(
        `3h`: Double? = null
    ) = SnowDto(
        `3h` = `3h` ?: 0.54
    )
}
