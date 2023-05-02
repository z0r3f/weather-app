package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.WeatherData
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.WeatherDataDto
import jakarta.inject.Singleton

@Singleton
class WeatherDataMapper(
    private val forecastMapper: ForecastMapper,
    private val locationMapper: LocationMapper
): Mapper<WeatherData, WeatherDataDto> {
    override fun toDto(model: WeatherData): WeatherDataDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: WeatherDataDto): WeatherData {
        return WeatherData(
            location = locationMapper.toModel(dto.city),
            forecasts = dto.list.map {
                it.timezone = dto.city.timezone
                forecastMapper.toModel(it)
            }
        )
    }
}
