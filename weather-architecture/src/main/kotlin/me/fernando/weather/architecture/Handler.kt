package me.fernando.weather.architecture

interface Handler <T, R : Request<T>> {
    operator fun invoke(request: R): T
}
