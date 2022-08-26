package me.fernando.chat.db.sql

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import me.fernando.chat.db.entities.ChatEntity
import me.fernando.chat.db.entities.FavoriteLocationEntity

@JdbcRepository(dialect = Dialect.POSTGRES)
interface FavoriteLocationPostgresRepository : CrudRepository<FavoriteLocationEntity, Long> {
    fun findOneByChatAndName(chat: ChatEntity, name: String): FavoriteLocationEntity?
}
