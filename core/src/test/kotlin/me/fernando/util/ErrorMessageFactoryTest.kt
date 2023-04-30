package me.fernando.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ErrorMessageFactoryTest {

    @Test
    internal fun testCoordinateIsMissing() {
        val cityName = "Delhi"
        assertEquals(
            "The coordinate of `$cityName` is missing",
            ErrorMessageFactory.coordinateIsMissing(cityName)
        )
    }
}
