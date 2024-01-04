package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.Current
import io.github.z0r3f.weather.core.forecast.domain.CurrentData
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.CurrentDataDto
import jakarta.inject.Singleton

@Singleton
class CurrentDataMapper(
    private val locationMapper: LocationMapper,
    private val weatherMapper: WeatherMapper,
): Mapper<CurrentData, CurrentDataDto> {
    override fun toDto(model: CurrentData): CurrentDataDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: CurrentDataDto): CurrentData {
        return CurrentData(
            location = locationMapper.toModel(dto),
            current = Current(
                temperature = dto.main.temp,
                feelsLike = dto.main.feelsLike,
                temperatureMin = dto.main.tempMin,
                temperatureMax = dto.main.tempMax,
                pressure = dto.main.pressure,
                humidity = dto.main.humidity,
                windSpeed = dto.wind.speed,
                windDirection = dto.wind.deg,
                windGust = dto.wind.gust,
                cloudiness = dto.clouds.all,
                visibility = dto.visibility,
                weather = weatherMapper.toModel(dto.weather),
            ),
        )
    }
}
