package me.fernando.weather.domain

import org.junit.jupiter.api.Test

internal class LocationTest {
    @Test
    fun creationLocation() {
        val location = Location(
            name = "London",
            country = "UK",
            latitude = 51.509865,
            longitude = -0.118092
        )
        assert(location.name == "London")
        assert(location.country == "UK")
        assert(location.latitude == 51.509865)
        assert(location.longitude == -0.118092)
    }
}
