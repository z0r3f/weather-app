package me.fernando.weather.domain

open class WeatherMother {
    open fun of(
        id: Int? = 801,
        main: String? = "Clouds",
        description: String? = "few clouds",
        icon: String? = "02d",
    ) = Weather(
        id = id,
        main = main,
        description = description,
        icon = icon,
    )
}
