package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.WeatherDto
import me.fernando.weather.domain.Weather

@Singleton
class WeatherMapper: Mapper<Weather, WeatherDto> {
    override fun toDto(entity: Weather): WeatherDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: WeatherDto): Weather {
        return Weather(
            dto.id,
            dto.main,
            dto.description,
            dto.icon
        )
    }
}
