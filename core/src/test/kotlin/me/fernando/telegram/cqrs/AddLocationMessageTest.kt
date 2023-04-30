package me.fernando.telegram.cqrs

import me.fernando.chat.domain.Chat
import org.junit.jupiter.api.Assertions.*
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
