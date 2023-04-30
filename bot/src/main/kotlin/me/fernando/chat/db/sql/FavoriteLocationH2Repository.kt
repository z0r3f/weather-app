package me.fernando.chat.db.sql

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import me.fernando.chat.db.entities.ChatEntity
import me.fernando.chat.db.entities.FavoriteLocationEntity

@JdbcRepository(dialect = Dialect.H2)
interface FavoriteLocationH2Repository : CrudRepository<FavoriteLocationEntity, Long> {
    fun findOneByChatAndName(chat: ChatEntity, name: String): FavoriteLocationEntity?
    fun findByChat(chat: ChatEntity): List<FavoriteLocationEntity>
}
