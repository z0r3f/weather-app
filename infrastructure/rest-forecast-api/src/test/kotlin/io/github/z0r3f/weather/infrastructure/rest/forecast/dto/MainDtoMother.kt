package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

open class MainDtoMother {
    open fun of(
        temp: Double? = null,
        feelsLike: Double? = null,
        tempMin: Double? = null,
        tempMax: Double? = null,
        pressure: Double? = null,
        seaLevel: Double? = null,
        grndLevel: Double? = null,
        humidity: Int? = null,
        tempKf: Double? = null,
    ): MainDto {
        return MainDto(
            temp = temp ?: 23.05,
            feelsLike = feelsLike ?: 22.51,
            tempMin = tempMin ?: 23.05,
            tempMax = tempMax ?: 23.9,
            pressure = pressure ?: 1021.0,
            seaLevel = seaLevel ?: 1012.0,
            grndLevel = grndLevel ?: 938.0,
            humidity = humidity ?: 42,
            tempKf = tempKf ?: -1.22,
        )
    }
}
