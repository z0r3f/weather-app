package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class RainDtoMother {
    open fun of(
        `3h`: Double? = null
    ) = RainDto(
        `3h` = `3h` ?: 0.15
    )
}
