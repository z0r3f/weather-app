package me.fernando.weather.api.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.GeographicalCoordinate
import jakarta.inject.Singleton
import me.fernando.weather.api.dto.GeographicalCoordinateDto

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
