package me.fernando.chat.db.mapper

import jakarta.inject.Singleton
import me.fernando.chat.db.entities.ChatEntity
import me.fernando.chat.domain.Chat
import me.fernando.weather.architecture.mapper.Mapper

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
