package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

// https://openweathermap.org/forecast5
data class WeatherDataDto(
    @JsonProperty("cod") val cod: String, // Internal parameter
    @JsonProperty("message") val message: Double, // Internal parameter
    @JsonProperty("cnt") val cnt: Int, // Internal parameter
    @JsonProperty("list") val list: List<ForecastDto>, // List of forecasts
    @JsonProperty("city") val city: CityDto // City
)
