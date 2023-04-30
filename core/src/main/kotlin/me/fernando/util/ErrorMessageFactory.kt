package me.fernando.util

class ErrorMessageFactory {
    companion object {
        fun coordinateIsMissing(cityName: String): String = "The coordinate of `$cityName` is missing"
    }
}
