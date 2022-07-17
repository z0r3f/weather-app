package me.fernando

import io.micronaut.runtime.Micronaut

object WeatherBot {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(WeatherBot::class.java)
    }
}
