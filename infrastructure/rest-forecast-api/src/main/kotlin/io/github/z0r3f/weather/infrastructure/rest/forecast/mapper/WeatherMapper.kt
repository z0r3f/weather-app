package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.Weather
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.WeatherDto
import jakarta.inject.Singleton

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
