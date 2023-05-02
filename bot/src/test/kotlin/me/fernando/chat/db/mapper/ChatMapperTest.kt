package me.fernando.chat.db.mapper

import me.fernando.chat.db.entities.ChatEntity
import me.fernando.chat.db.entities.FavoriteLocationEntity
import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class ChatMapperTest {

    private val favoriteLocationMapper = mock<FavoriteLocationMapper>()
    private lateinit var sut: ChatMapper

    @BeforeEach
    fun setUp() {
        sut = ChatMapper(favoriteLocationMapper)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(favoriteLocationMapper)
    }

    @Test
    fun `toDto should map Chat to ChatEntity`() {
        val chat = Chat(
            id = 1234L,
            title = "Test Chat",
            username = "testchat",
            favoriteLocations = setOf(NEW_YORK_MODEL)
        )
        val expectedEntity = ChatEntity().apply {
            id = chat.id
            title = chat.title
            username = chat.username
            favoriteLocations = hashSetOf(NEW_YORK_ENTITY)
        }
        whenever(favoriteLocationMapper.toDto(NEW_YORK_MODEL)).thenReturn(NEW_YORK_ENTITY)

        val entity = sut.toDto(chat)

        assertEquals(expectedEntity.id, entity.id)
        assertEquals(expectedEntity.title, entity.title)
        assertEquals(expectedEntity.username, entity.username)
        assertEquals(expectedEntity.favoriteLocations, entity.favoriteLocations)
        verify(favoriteLocationMapper).toDto(NEW_YORK_MODEL)
    }

    @Test
    fun `toModel should map ChatEntity to Chat`() {
        val entity = ChatEntity().apply {
            id = 1234L
            title = "Test Chat"
            username = "testchat"
            favoriteLocations = hashSetOf(NEW_YORK_ENTITY)
        }
        val expectedChat = Chat(
            id = entity.id,
            title = entity.title,
            username = entity.username,
            favoriteLocations = setOf(NEW_YORK_MODEL)
        )
        whenever(favoriteLocationMapper.toModel(NEW_YORK_ENTITY)).thenReturn(NEW_YORK_MODEL)

        val chat = sut.toModel(entity)

        assertEquals(expectedChat.id, chat.id)
        assertEquals(expectedChat.title, chat.title)
        assertEquals(expectedChat.username, chat.username)
        assertEquals(expectedChat.favoriteLocations, chat.favoriteLocations)
        verify(favoriteLocationMapper).toModel(NEW_YORK_ENTITY)
    }

    private companion object {
        val NEW_YORK_MODEL = FavoriteLocation(
            id = 1,
            name = "New York",
            latitude = 40.7128,
            longitude = -74.0060,
            country = "USA"
        )
        val NEW_YORK_ENTITY = FavoriteLocationEntity(
            id = 1,
            name = "New York",
            latitude = 40.7128,
            longitude = -74.0060,
            country = "USA"
        )
    }
}
