package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.WeatherDataDto
import me.fernando.weather.architecture.mapper.Mapper
import me.fernando.weather.domain.WeatherData

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
