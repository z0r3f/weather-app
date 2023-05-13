package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WindDto (
    @JsonProperty("speed") val speed: Double, // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    @JsonProperty("deg") val deg: Double, // Wind direction, degrees (meteorological)
    @JsonProperty("gust") val gust: Double // Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
)
