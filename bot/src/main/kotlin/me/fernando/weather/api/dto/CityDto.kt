package me.fernando.weather.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CityDto(
    @JsonProperty("id") val id: Long,  // City ID
    @JsonProperty("name") val name: String, // City name
    @JsonProperty("country") val country: String, // Country code (GB, JP etc.)
    @JsonProperty("coord") val coordinate: CoordinateDto, // Coordinate of the city
    @JsonProperty("population") val population: Long, // Population of the city
    @JsonProperty("timezone") val timezone: Int, // Timezone of the city
    @JsonProperty("sunrise") val sunrise: Long, // Sunrise time
    @JsonProperty("sunset") val sunset: Long // Sunset time
)
