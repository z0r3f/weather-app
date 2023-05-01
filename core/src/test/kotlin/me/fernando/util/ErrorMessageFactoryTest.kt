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

    @Test
    internal fun testCallbackUnknown() {
        assertEquals(
            "Callback unknown",
            ErrorMessageFactory.callbackUnknown()
        )
    }

    @Test
    internal fun testNotFoundFavoriteLocation() {
        assertEquals(
            "Not found favorite location",
            ErrorMessageFactory.notFoundFavoriteLocation()
        )
    }

    @Test
    internal fun testInvalidHourOfDay() {
        val hourOfDay = " 23 "
        assertEquals(
            "Invalid hour of day: \"$hourOfDay\". Should be an integer between 0 and 23",
            ErrorMessageFactory.invalidHourOfDay(hourOfDay)
        )
    }

    @Test
    internal fun testHourOfDayShouldBeBetweenValidRange() {
        assertEquals(
            "Hour of day must be between 0 and 23",
            ErrorMessageFactory.hourOfDayShouldBeBetweenValidRange()
        )
    }

    @Test
    internal fun testCommandNotSupported() {
        assertEquals(
            "Command not supported",
            ErrorMessageFactory.commandNotSupported()
        )
    }
}
