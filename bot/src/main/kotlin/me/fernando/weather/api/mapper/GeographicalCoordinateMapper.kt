package me.fernando.weather.api.mapper

import jakarta.inject.Singleton
import me.fernando.weather.api.dto.GeographicalCoordinateDto
import me.fernando.weather.architecture.mapper.Mapper
import me.fernando.weather.domain.GeographicalCoordinate

@Singleton
class GeographicalCoordinateMapper: Mapper<GeographicalCoordinate, GeographicalCoordinateDto> {
    override fun toDto(model: GeographicalCoordinate): GeographicalCoordinateDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: GeographicalCoordinateDto): GeographicalCoordinate {
        return GeographicalCoordinate(
            name = dto.name,
            latitude = dto.latitude,
            longitude = dto.longitude,
            country = dto.country,
        )
    }
}
