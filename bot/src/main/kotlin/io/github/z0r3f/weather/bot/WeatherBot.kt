package io.github.z0r3f.weather.bot

import io.micronaut.runtime.Micronaut

object WeatherBot {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(WeatherBot::class.java)
    }
}
