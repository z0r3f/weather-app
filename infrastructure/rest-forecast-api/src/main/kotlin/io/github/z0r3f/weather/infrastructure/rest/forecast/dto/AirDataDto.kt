package io.github.z0r3f.weather.infrastructure.rest.forecast.dto

import com.fasterxml.jackson.annotation.JsonProperty

// https://openweathermap.org/api/air-pollution#descr
data class AirDataDto(
    @JsonProperty("coord") val coordinate: CoordinateDto,
    @JsonProperty("list") val list: List<AirDataListDto>,
)

data class AirDataListDto(
    @JsonProperty("dt") val dt: Long,
    @JsonProperty("main") val main: AirMainDto,
    @JsonProperty("components") val components: ComponentsDto,
)

data class AirMainDto(
    @JsonProperty("aqi") val aqi: Int,
)

data class ComponentsDto(
    @JsonProperty("co") val co: Double,
    @JsonProperty("no") val no: Double,
    @JsonProperty("no2") val no2: Double,
    @JsonProperty("o3") val o3: Double,
    @JsonProperty("so2") val so2: Double,
    @JsonProperty("pm2_5") val pm2_5: Double,
    @JsonProperty("pm10") val pm10: Double,
    @JsonProperty("nh3") val nh3: Double,
)
