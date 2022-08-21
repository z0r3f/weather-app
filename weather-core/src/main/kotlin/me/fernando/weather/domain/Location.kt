package me.fernando.weather.domain

data class Location(
    val name: String? = null,
    val country: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val population: Long? = null,
    val timezone: Long? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
) {
    fun isInvalid() = (latitude == null) || latitude.isNaN() || (longitude == null) || longitude.isNaN()
}
