package io.github.z0r3f.weather.core.forecast.domain

open class CurrentDataMother {
    open fun of(
        location: Location? = LocationMother().of(),
        current: Current? = CurrentMother().of(),
    ): CurrentData {
        return CurrentData(
            location = location,
            current = current,
        )
    }
}
