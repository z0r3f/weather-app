package io.github.z0r3f.weather.architecture.mapper

interface Mapper<T, R> {
    fun toDto(model: T): R
    fun toModel(dto: R): T
}
