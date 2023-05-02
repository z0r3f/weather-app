package io.github.z0r3f.weather.infrastructure.db.chat.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.infrastructure.db.chat.entities.FavoriteLocationEntity
import jakarta.inject.Singleton

@Singleton
open class FavoriteLocationMapper: Mapper<FavoriteLocation, FavoriteLocationEntity> {
    override fun toDto(model: FavoriteLocation): FavoriteLocationEntity {
        val entity = FavoriteLocationEntity()
        entity.id = model.id
        entity.name = model.name
        entity.latitude = model.latitude
        entity.longitude = model.longitude
        entity.country = model.country
        return entity
    }

    override fun toModel(dto: FavoriteLocationEntity): FavoriteLocation {
        return FavoriteLocation(
            id = dto.id,
            name = dto.name,
            latitude = dto.latitude,
            longitude = dto.longitude,
            country = dto.country
        )
    }
}
