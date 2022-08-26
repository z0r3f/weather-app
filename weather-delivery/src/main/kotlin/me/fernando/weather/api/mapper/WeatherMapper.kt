package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.WeatherDto
import me.fernando.weather.architecture.mapper.Mapper
import me.fernando.weather.domain.Weather

@Singleton
open class WeatherMapper: Mapper<Weather, WeatherDto> {
    override fun toDto(model: Weather): WeatherDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: WeatherDto): Weather {
        return Weather(
            dto.id,
            dto.main,
            dto.description,
            dto.icon
        )
    }
}
