package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.CityDto
import me.fernando.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.Location

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
