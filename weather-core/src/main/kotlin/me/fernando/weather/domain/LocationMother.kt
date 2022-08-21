package me.fernando.weather.domain

open class LocationMother {

    open fun of(
        name: String? = "Madrid",
        country: String? = "Spain",
        latitude: Double? = 40.4167,
        longitude: Double? = -3.7033,
        population: Long? = 1000000,
        timezone: Long? = 7200,
        sunrise: Long? = 1652677343,
        sunset: Long? = 1652728918,
    ): Location {
        return Location(
            name = name,
            country = country,
            latitude = latitude,
            longitude = longitude,
            population = population,
            timezone = timezone,
            sunrise = sunrise,
            sunset = sunset
        )
    }
}
