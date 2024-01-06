package io.github.z0r3f.weather.core.forecast.domain

open class AirDataMother {
    open fun of(
        location: Location? = LocationMother().of(),
        air: Air? = AirMother().of(),
    ): AirData {
        return AirData(
            location = location,
            air = air,
        )
    }
}
