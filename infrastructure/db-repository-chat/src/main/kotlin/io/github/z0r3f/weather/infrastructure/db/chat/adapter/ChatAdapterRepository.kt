package io.github.z0r3f.weather.infrastructure.db.chat.adapter

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.chat.port.ChatRepository
import io.github.z0r3f.weather.core.util.isNull
import io.github.z0r3f.weather.infrastructure.db.chat.entities.AlertEntity
import io.github.z0r3f.weather.infrastructure.db.chat.mapper.ChatMapper
import io.github.z0r3f.weather.infrastructure.db.chat.mapper.FavoriteLocationMapper
import io.github.z0r3f.weather.infrastructure.db.chat.sql.AlertH2Repository
import io.github.z0r3f.weather.infrastructure.db.chat.sql.ChatH2Repository
import io.github.z0r3f.weather.infrastructure.db.chat.sql.FavoriteLocationH2Repository
import jakarta.inject.Singleton

@Singleton
open class ChatAdapterRepository(
    private val chatH2Repository: ChatH2Repository,
    private val favoriteLocationH2Repository: FavoriteLocationH2Repository,
    private val alertH2Repository: AlertH2Repository,
    private val chatMapper: ChatMapper,
    private val favoriteLocationMapper: FavoriteLocationMapper,
) : ChatRepository {
    override fun addFavoriteLocation(chat: Chat, favoriteLocation: FavoriteLocation) {
        val chatEntityOptional = chatH2Repository.findById(chat.id)

        val chatEntity = if (chatEntityOptional.isPresent) {
            chatEntityOptional.get()
        } else {
            val chatEntity = chatMapper.toDto(chat)
            chatH2Repository.save(chatEntity)
        }
        val favoriteLocationEntity = favoriteLocationMapper.toDto(favoriteLocation)
        favoriteLocationEntity.chat = chatEntity

        favoriteLocation.name?.let { name ->
            if (favoriteLocationH2Repository.findOneByChatAndName(chatEntity, name).isNull()) {
                favoriteLocationH2Repository.save(favoriteLocationEntity)
            }
        }
    }

    override fun getFavoriteLocation(chat: Chat, cityName: String): FavoriteLocation? =
        favoriteLocationH2Repository.findOneByChatAndName(
            chatMapper.toDto(chat), cityName
        )?.let { favoriteLocationMapper.toModel(it) }

    override fun removeFavoriteLocation(chat: Chat, favoriteLocation: FavoriteLocation) {
        favoriteLocationH2Repository.delete(favoriteLocationMapper.toDto(favoriteLocation))
    }

    override fun getFavoriteLocations(chat: Chat) =
        favoriteLocationH2Repository.findByChat(chatMapper.toDto(chat))
            .map { favoriteLocationMapper.toModel(it) }

    override fun addAlert(chat: Chat, hourOfDay: Int) {
        val chatEntityOptional = chatH2Repository.findById(chat.id)
        if (!chatEntityOptional.isPresent) {
            throw IllegalArgumentException("Chat not found")
        }
        val favoritesLocations = favoriteLocationH2Repository.findByChat(chatEntityOptional.get())
        if (favoritesLocations.isEmpty()) {
            throw IllegalArgumentException("No favorite locations found")
        }
        val alert = AlertEntity(
            hourOfDay = hourOfDay,
        )
        alert.chat = chatEntityOptional.get()
        alertH2Repository.save(alert)
    }

    override fun getAlerts(hourOfDay: Int): List<Chat> {
        return alertH2Repository.findByHourOfDay(hourOfDay)
            .map { chatMapper.toModel(it.chat!!) }
    }

    override fun deleteAlert(chat: Chat, hourOfDay: Int) {
        alertH2Repository.deleteByChatIdAndHourOfDay(chatId = chat.id, hourOfDay = hourOfDay)
    }
}
