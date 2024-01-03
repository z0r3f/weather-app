package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

// https://openweathermap.org/current
data class CurrentDataDto(
    @JsonProperty("coord") val coordinate: CoordinateDto,
    @JsonProperty("weather") val weather: WeatherDto,
    @JsonProperty("main") val main: MainDto,
    @JsonProperty("visibility") val visibility: Int, // meters
    @JsonProperty("wind") val wind: WindDto,
    @JsonProperty("rain") val rain: RainDto? = null,
    @JsonProperty("clouds") val clouds: CloudsDto,
    @JsonProperty("dt") val dt: Long,
    @JsonProperty("sys") val sys: SysDto,
    @JsonProperty("timezone") val timezone: Int, // seconds
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
)
