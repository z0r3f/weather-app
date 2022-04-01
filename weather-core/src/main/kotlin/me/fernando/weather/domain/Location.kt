package me.fernando.weather.domain

data class Location(
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val population: Int? = null,
    val timezone: Int? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null
)
