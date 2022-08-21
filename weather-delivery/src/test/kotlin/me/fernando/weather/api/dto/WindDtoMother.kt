package me.fernando.weather.api.dto

open class WindDtoMother {
    open fun of(
        speed: Double? = null,
        deg: Double? = null,
        gust: Double? = null,
    ) = WindDto(
        speed = speed ?: 2.53,
        deg = deg ?: 146.0,
        gust = gust ?: 4.06
    )
}
