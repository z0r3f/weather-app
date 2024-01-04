package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class SysDtoMother {
    open fun of(
        type: Int? = null,
        id: Int? = null,
        country: String? = null,
        sunrise: Long? = null,
        sunset: Long? = null,
    ) = SysDto(
        type = type ?: 2,
        id = id ?: 2003459,
        country = country ?: "ES",
        sunrise = sunrise ?: 1704267281,
        sunset = sunset ?: 1704301487,
    )
}
