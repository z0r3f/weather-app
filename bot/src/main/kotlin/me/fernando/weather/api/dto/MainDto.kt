package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MainDto (

    @JsonProperty("temp")
    // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    val temp: Double,

    @JsonProperty("feels_like")
    // Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    val feelsLike: Double,

    @JsonProperty("temp_min")
    // Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    val tempMin: Double,

    @JsonProperty("temp_max")
    // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    val tempMax: Double,

    @JsonProperty("pressure")
    // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    val pressure: Double,

    @JsonProperty("sea_level")
    // Atmospheric pressure on the sea level, hPa
    val seaLevel: Double,

    @JsonProperty("grnd_level")
    // Atmospheric pressure on the ground level, hPa
    val grndLevel: Double,

    @JsonProperty("humidity")
    // Humidity, %
    val humidity: Int,

    @JsonProperty("temp_kf")
    // Internal parameter
    val tempKf: Double,
)
