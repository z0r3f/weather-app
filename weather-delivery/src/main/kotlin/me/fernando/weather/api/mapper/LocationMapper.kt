package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.CityDto
import me.fernando.weather.domain.Location

@Singleton
class LocationMapper: Mapper<Location, CityDto> {
    override fun toDto(entity: Location): CityDto {
        TODO("Not yet implemented")
    }

    override fun toEntity(dto: CityDto): Location {
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
