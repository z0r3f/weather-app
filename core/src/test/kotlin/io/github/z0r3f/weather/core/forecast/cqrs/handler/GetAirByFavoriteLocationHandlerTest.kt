package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.forecast.cqrs.GetAirByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.port.AirRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class GetAirByFavoriteLocationHandlerTest {

    private val airRepository: AirRepository = mock()

    private lateinit var sut: GetAirByFavoriteLocationHandler

    @BeforeEach
    internal fun setUp() {
        sut = GetAirByFavoriteLocationHandler(airRepository)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(airRepository)
    }

    @Test
    internal fun `should get air quality by favorite location`() {
        val chat = mock<Chat>()
        val favoriteLocation = FavoriteLocation(chat = chat, name = "Tokyo", latitude = -35.6895, longitude = 139.6917, country = "Japan")

        sut.handle(GetAirByFavoriteLocationMessage(favoriteLocation))

        verify(airRepository).getAirQuality(favoriteLocation.latitude!!, favoriteLocation.longitude!!)
    }
}
