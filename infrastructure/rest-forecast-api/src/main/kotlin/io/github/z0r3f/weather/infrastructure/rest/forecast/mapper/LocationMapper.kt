package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.Location
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.CityDto
import jakarta.inject.Singleton

@Singleton
class LocationMapper: Mapper<Location, CityDto> {
    override fun toDto(model: Location): CityDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: CityDto): Location {
        return Location(
            name = dto.name,
            country = dto.country,
            latitude = dto.coordinate.latitude,
            longitude = dto.coordinate.longitude,
            population = dto.population,
            timezone = dto.timezone,
            sunrise = dto.sunrise,
            sunset = dto.sunset,
        )
    }
}
