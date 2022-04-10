package me.fernando.weather.architecture

interface Command<out T> : Request<T>
