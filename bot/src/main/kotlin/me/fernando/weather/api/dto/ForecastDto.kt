package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ForecastDto(
    @JsonProperty("dt") val dt: Long,
    @JsonProperty("main") val main: MainDto,
    @JsonProperty("weather") val weather: List<WeatherDto>,
    @JsonProperty("clouds") val clouds: CloudsDto,
    @JsonProperty("wind") val wind: WindDto,
    @JsonProperty("rain") val rain: RainDto? = null,
    @JsonProperty("snow") val snow: SnowDto? = null,
    @JsonProperty("visibility") val visibility: Int, // meters
    @JsonProperty("pop") val probabilityOfPrecipitation: Double, // probability of precipitation
    @JsonProperty("dt_txt") val dateTime: String,
) {
    var timezone: Int = 0
}
