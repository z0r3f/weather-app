package io.github.z0r3f.weather.infrastructure.rest.forecast.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.forecast.domain.Air
import io.github.z0r3f.weather.core.forecast.domain.AirData
import io.github.z0r3f.weather.infrastructure.rest.forecast.dto.AirDataDto
import jakarta.inject.Singleton

@Singleton
class AirDataMapper(
    private val locationMapper: LocationMapper,
): Mapper<AirData, AirDataDto> {
    override fun toDto(model: AirData): AirDataDto {
        TODO("Not yet implemented")
    }

    override fun toModel(dto: AirDataDto): AirData {
        val airDataDto = dto.list.firstOrNull() ?: return AirData(
            location = locationMapper.toModel(dto),
            air = Air(
                airQualityIndex = null,
            ),
        )
        return AirData(
            location = locationMapper.toModel(dto),
            air = Air(
                airQualityIndex = airDataDto.main.aqi,
                carbonMonoxide = airDataDto.components.co,
                nitrogenMonoxide = airDataDto.components.no,
                nitrogenDioxide = airDataDto.components.no2,
                ozone = airDataDto.components.o3,
                sulphurDioxide = airDataDto.components.so2,
                fineParticlesMatter = airDataDto.components.pm2_5,
                coarseParticulateMatter = airDataDto.components.pm10,
                ammonia = airDataDto.components.nh3,
            ),
        )
    }
}
