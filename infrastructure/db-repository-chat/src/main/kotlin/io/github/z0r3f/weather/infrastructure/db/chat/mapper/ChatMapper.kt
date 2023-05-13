package io.github.z0r3f.weather.infrastructure.db.chat.mapper

import io.github.z0r3f.weather.architecture.mapper.Mapper
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.infrastructure.db.chat.entities.ChatEntity
import jakarta.inject.Singleton

@Singleton
open class ChatMapper(
    private val favoriteLocationMapper: FavoriteLocationMapper
): Mapper<Chat, ChatEntity> {
    override fun toDto(model: Chat): ChatEntity {
        val entity = ChatEntity()
        entity.id = model.id
        entity.title = model.title
        entity.username = model.username
        entity.favoriteLocations = model.favoriteLocations.map { favoriteLocationMapper.toDto(it) }.toHashSet()
        return entity
    }

    override fun toModel(dto: ChatEntity): Chat {
        return Chat(
            id = dto.id,
            title = dto.title,
            username = dto.username,
            favoriteLocations = dto.favoriteLocations.map { favoriteLocationMapper.toModel(it) }.toHashSet()
        )
    }
}
