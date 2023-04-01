package me.fernando.chat.db.sql

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import me.fernando.chat.db.entities.AlertEntity

@JdbcRepository(dialect = Dialect.H2)
interface AlertH2Repository : CrudRepository<AlertEntity, Long> {
    fun findByHourOfDay(hourOfDay: Int): List<AlertEntity>
    fun deleteByChatIdAndHourOfDay(chatId: Long, hourOfDay: Int)
}