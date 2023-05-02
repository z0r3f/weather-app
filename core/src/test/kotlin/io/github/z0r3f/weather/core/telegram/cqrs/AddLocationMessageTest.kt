package io.github.z0r3f.weather.core.telegram.cqrs

import io.github.z0r3f.weather.core.chat.domain.Chat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AddLocationMessageTest {

    @Test
    internal fun testCityName() {
        val chat = Chat()
        val cityName = "New York"
        val message = AddLocationMessage(chat, cityName)
        assertEquals(cityName, message.cityName)
    }
}
