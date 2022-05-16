package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.WeatherDataDto
import me.fernando.weather.domain.WeatherData

@Singleton
class WeatherDataMapper(
    private val forecastMapper: ForecastMapper,
    private val locationMapper: LocationMapper
): Mapper<WeatherData, WeatherDataDto> {
    override fun toDto(entity: WeatherData): WeatherDataDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: WeatherDataDto): WeatherData {
        return WeatherData(
            location = locationMapper.toEntity(dto.city),
            forecasts = dto.list.map { forecastMapper.toEntity(it) }
        )
    }
}
