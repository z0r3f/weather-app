package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class SnowDtoMother {
    open fun of(
        `3h`: Double? = null
    ) = SnowDto(
        `3h` = `3h` ?: 0.54
    )
}
