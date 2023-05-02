package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class WeatherDtoMother {
    open fun of(
        id: Int? = null,
        main: String? = null,
        description: String? = null,
        icon: String? = null,
    ) = WeatherDto(
        id = id ?: 801,
        main = main ?: "Clouds",
        description = description ?: "few clouds",
        icon = icon ?: "02d"
    )
}
