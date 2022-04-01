package me.fernando.weather.domain

import org.junit.jupiter.api.Test

class LocationTest {
    @Test
    fun creationLocation() {
        val location = Location(
            name = "London",
            country = "UK",
            lat = 51.509865,
            lon = -0.118092
        )
        assert(location.name == "London")
        assert(location.country == "UK")
        assert(location.lat == 51.509865)
        assert(location.lon == -0.118092)
    }
}
