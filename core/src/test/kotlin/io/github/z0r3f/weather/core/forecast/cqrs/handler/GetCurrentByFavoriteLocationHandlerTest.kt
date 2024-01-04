package io.github.z0r3f.weather.core.forecast.cqrs.handler

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.core.forecast.cqrs.GetCurrentByFavoriteLocationMessage
import io.github.z0r3f.weather.core.forecast.port.CurrentRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

internal class GetCurrentByFavoriteLocationHandlerTest {

    private val currentRepository: CurrentRepository = mock()

    private lateinit var sut: GetCurrentByFavoriteLocationHandler

    @BeforeEach
    internal fun setUp() {
        sut = GetCurrentByFavoriteLocationHandler(currentRepository)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(currentRepository)
    }

    @Test
    internal fun `should get current weather by favorite location`() {
        val chat = mock<Chat>()
        val favoriteLocation = FavoriteLocation(chat = chat, name = "Tokyo", latitude = -35.6895, longitude = 139.6917, country = "Japan")

        sut.handle(GetCurrentByFavoriteLocationMessage(favoriteLocation))

        verify(currentRepository).getCurrent(favoriteLocation.latitude!!, favoriteLocation.longitude!!)
    }
}
