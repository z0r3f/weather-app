package io.github.z0r3f.weather.infrastructure.db.chat.sql

import io.github.z0r3f.weather.infrastructure.db.chat.entities.ChatEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface ChatH2Repository : CrudRepository<ChatEntity, Long>
