package me.fernando.telegram.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import me.fernando.telegram.domain.Chat

@JdbcRepository(dialect = Dialect.POSTGRES)
interface ChatRepository : CrudRepository<Chat, Long>
