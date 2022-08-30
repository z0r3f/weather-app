package me.fernando.chat.db.sql

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import me.fernando.chat.db.entities.ChatEntity

@JdbcRepository(dialect = Dialect.POSTGRES)
interface ChatPostgresRepository : CrudRepository<ChatEntity, Long>