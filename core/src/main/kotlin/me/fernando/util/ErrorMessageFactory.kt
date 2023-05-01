package me.fernando.util

class ErrorMessageFactory {
    companion object {
        fun coordinateIsMissing(cityName: String): String = "The coordinate of `$cityName` is missing"
        fun callbackUnknown() = "Callback unknown"
        fun notFoundFavoriteLocation() = "Not found favorite location"
        fun invalidHourOfDay(hourOfDay: String) = "Invalid hour of day: \"$hourOfDay\". Should be an integer between 0 and 23"
        fun hourOfDayShouldBeBetweenValidRange() = "Hour of day must be between 0 and 23"
        fun commandNotSupported() = "Command not supported"
    }
}
