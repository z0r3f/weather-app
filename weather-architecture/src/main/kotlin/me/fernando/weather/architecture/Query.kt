package me.fernando.weather.architecture

interface Query<out T> : Request<T>
