package me.fernando.weather.architecture

interface Pipeline {
    fun <T> dispatch(query: Query<T>): T
    fun <T> dispatch(command: Command<T>): T
}
