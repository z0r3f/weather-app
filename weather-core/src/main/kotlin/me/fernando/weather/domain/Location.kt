package me.fernando.weather.domain

data class Location(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val population: Long? = null,
    val timezone: Long? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null
)
