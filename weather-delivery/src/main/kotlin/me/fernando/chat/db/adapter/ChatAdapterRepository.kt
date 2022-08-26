package me.fernando.chat.db.adapter

import jakarta.inject.Singleton
import me.fernando.chat.db.mapper.ChatMapper
import me.fernando.chat.db.mapper.FavoriteLocationMapper
import me.fernando.chat.db.sql.ChatPostgresRepository
import me.fernando.chat.db.sql.FavoriteLocationPostgresRepository
import me.fernando.chat.domain.Chat
import me.fernando.chat.domain.FavoriteLocation
import me.fernando.chat.port.ChatRepository
import me.fernando.util.isNull

@Singleton
open class ChatAdapterRepository(
    private val chatPostgresRepository: ChatPostgresRepository,
    private val favoriteLocationPostgresRepository: FavoriteLocationPostgresRepository,
    private val chatMapper: ChatMapper,
    private val favoriteLocationMapper: FavoriteLocationMapper,
) : ChatRepository {
    override fun addLocationFavorite(chat: Chat, favoriteLocation: FavoriteLocation) {
        val chatEntityOptional = chatPostgresRepository.findById(chat.id)

        val chatEntity = if (chatEntityOptional.isPresent) {
            chatEntityOptional.get()
        } else {
            val chatEntity = chatMapper.toDto(chat)
            chatPostgresRepository.save(chatEntity)
        }
        val favoriteLocationEntity = favoriteLocationMapper.toDto(favoriteLocation)
        favoriteLocationEntity.chat = chatEntity

        favoriteLocation.name?.let { name ->
            if (favoriteLocationPostgresRepository.findOneByChatAndName(chatEntity, name).isNull()) {
                favoriteLocationPostgresRepository.save(favoriteLocationEntity)
            }
        }
    }

    override fun removeLocationFavorite(chat: Chat, favoriteLocation: FavoriteLocation) {
        chatPostgresRepository.findById(chat.id).ifPresent {
            it.favoriteLocations.remove(favoriteLocationMapper.toDto(favoriteLocation))
            chatPostgresRepository.update(it)
        }
    }
}
