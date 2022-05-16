package me.fernando.weather.api.mapper

interface Mapper<T, R> {
    fun toDto(entity: T): R
    fun toEntity(dto: R): T
}
