package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class RainDtoMother {
    open fun of(
        `3h`: Double? = null,
        `1h`: Double? = null,
    ) = RainDto(
        `3h` = `3h` ?: 0.15,
        `1h` = `1h` ?: 0.05,
    )
}
