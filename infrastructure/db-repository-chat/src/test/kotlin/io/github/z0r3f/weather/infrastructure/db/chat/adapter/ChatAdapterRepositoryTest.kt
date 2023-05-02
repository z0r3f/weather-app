package io.github.z0r3f.weather.infrastructure.db.chat.adapter

import io.github.z0r3f.weather.core.chat.domain.Chat
import io.github.z0r3f.weather.core.chat.domain.FavoriteLocation
import io.github.z0r3f.weather.infrastructure.db.chat.entities.AlertEntity
import io.github.z0r3f.weather.infrastructure.db.chat.entities.ChatEntity
import io.github.z0r3f.weather.infrastructure.db.chat.entities.FavoriteLocationEntity
import io.github.z0r3f.weather.infrastructure.db.chat.mapper.ChatMapper
import io.github.z0r3f.weather.infrastructure.db.chat.mapper.FavoriteLocationMapper
import io.github.z0r3f.weather.infrastructure.db.chat.sql.AlertH2Repository
import io.github.z0r3f.weather.infrastructure.db.chat.sql.ChatH2Repository
import io.github.z0r3f.weather.infrastructure.db.chat.sql.FavoriteLocationH2Repository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

internal class ChatAdapterRepositoryTest {

    private val chatH2Repository: ChatH2Repository = mock()
    private val favoriteLocationH2Repository: FavoriteLocationH2Repository = mock()
    private val alertH2Repository: AlertH2Repository = mock()
    private val chatMapper: ChatMapper = mock()
    private val favoriteLocationMapper: FavoriteLocationMapper = mock()

    private lateinit var sut: ChatAdapterRepository

    @BeforeEach
    internal fun setUp() {
        sut = ChatAdapterRepository(chatH2Repository, favoriteLocationH2Repository, alertH2Repository, chatMapper, favoriteLocationMapper)
    }

    @AfterEach
    internal fun tearDown() {
        verifyNoMoreInteractions(chatH2Repository, favoriteLocationH2Repository, alertH2Repository, chatMapper, favoriteLocationMapper)
    }

    @Test
    internal fun `Adding a new favourite location to an existing chat`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(id = 1)
        val sidney = FavoriteLocation(name = "Sidney", latitude = 37.7749, longitude = -122.4194)
        val sidneyEntity = FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        whenever(chatH2Repository.findById(chat.id)).thenReturn(Optional.of(chatEntity))
        whenever(favoriteLocationMapper.toDto(sidney)).thenReturn(sidneyEntity)
        whenever(favoriteLocationH2Repository.findOneByChatAndName(chatEntity, sidney.name!!)).thenReturn(null)

        sut.addFavoriteLocation(chat, sidney)

        verify(chatH2Repository).findById(chat.id)
        verify(favoriteLocationMapper).toDto(sidney)
        verify(favoriteLocationH2Repository).findOneByChatAndName(chatEntity, sidney.name!!)
        verify(favoriteLocationH2Repository).save(sidneyEntity)
    }

    @Test
    internal fun `Adding a favourite location with a duplicate name`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(id = 1)
        val sidney = FavoriteLocation(name = "Sidney", latitude = 37.7749, longitude = -122.4194)
        val sidneyEntity = FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        whenever(chatH2Repository.findById(chat.id)).thenReturn(Optional.of(chatEntity))
        whenever(favoriteLocationMapper.toDto(sidney)).thenReturn(sidneyEntity)
        whenever(favoriteLocationH2Repository.findOneByChatAndName(chatEntity, sidney.name!!)).thenReturn(sidneyEntity)

        sut.addFavoriteLocation(chat, sidney)

        verify(chatH2Repository).findById(chat.id)
        verify(favoriteLocationMapper).toDto(sidney)
        verify(favoriteLocationH2Repository).findOneByChatAndName(chatEntity, sidney.name!!)
        verify(favoriteLocationH2Repository, never()).save(sidneyEntity)
    }

    @Test
    internal fun `Adding a favourite location to a chat that does not exist`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(id = 1)
        val sidney = FavoriteLocation(name = "Sidney", latitude = 37.7749, longitude = -122.4194)
        val sidneyEntity = FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        whenever(chatH2Repository.findById(chat.id)).thenReturn(Optional.empty())
        whenever(chatMapper.toDto(chat)).thenReturn(chatEntity)
        whenever(chatH2Repository.save(chatEntity)).thenReturn(chatEntity)
        whenever(favoriteLocationMapper.toDto(sidney)).thenReturn(sidneyEntity)
        whenever(favoriteLocationH2Repository.findOneByChatAndName(chatEntity, sidney.name!!)).thenReturn(null)

        sut.addFavoriteLocation(chat, sidney)

        verify(chatH2Repository).findById(chat.id)
        verify(chatMapper).toDto(chat)
        verify(chatH2Repository).save(chatEntity)
        verify(favoriteLocationMapper).toDto(sidney)
        verify(favoriteLocationH2Repository).findOneByChatAndName(chatEntity, sidney.name!!)
        verify(favoriteLocationH2Repository).save(sidneyEntity)
    }

    @Test
    fun `getFavoriteLocation should return null when there's no matching favorite location`() {
        val cityName = "Non-existent city"
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(1, "user_id")
        whenever(chatMapper.toDto(chat)).thenReturn(chatEntity)
        whenever(favoriteLocationH2Repository.findOneByChatAndName(chatEntity, cityName)).thenReturn(null)

        val result = sut.getFavoriteLocation(chat, cityName)

        assertNull(result)
        verify(chatMapper).toDto(chat)
        verify(favoriteLocationH2Repository).findOneByChatAndName(chatEntity, cityName)
    }

    @Test
    fun `getFavoriteLocation should return a FavoriteLocation object when there's a matching favorite location`() {
        val sidneyName = "Sidney"
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(1, "user_id")
        val sidney = FavoriteLocation(name = sidneyName, latitude = 37.7749, longitude = -122.4194)
        val sidneyEntity = FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        whenever(chatMapper.toDto(chat)).thenReturn(chatEntity)
        whenever(favoriteLocationH2Repository.findOneByChatAndName(chatEntity, sidneyName)).thenReturn(sidneyEntity)
        whenever(favoriteLocationMapper.toModel(sidneyEntity)).thenReturn(sidney)

        val result = sut.getFavoriteLocation(chat, sidneyName)

        assertNotNull(result)
        assertEquals(sidneyName, result!!.name)
        verify(chatMapper).toDto(chat)
        verify(favoriteLocationH2Repository).findOneByChatAndName(chatEntity, sidneyName)
        verify(favoriteLocationMapper).toModel(sidneyEntity)
    }

    @Test
    fun `remove favorite location from chat`() {
        val sidneyName = "Sidney"
        val chat = Chat(id = 1)
        val sidneyEntity = FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        val sidney = FavoriteLocation(name = sidneyName, latitude = 37.7749, longitude = -122.4194)
        whenever(favoriteLocationMapper.toDto(sidney)).thenReturn(sidneyEntity)

        sut.removeFavoriteLocation(chat, sidney)

        verify(favoriteLocationMapper).toDto(sidney)
        verify(favoriteLocationH2Repository).delete(sidneyEntity)
    }

    @Test
    fun `get favorite locations from chat`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(1, "user_id")
        val favoriteLocationEntity1 = FavoriteLocationEntity(name = "Test city 1", latitude = 0.0, longitude = 0.0)
        val favoriteLocationEntity2 = FavoriteLocationEntity(name = "Test city 2", latitude = 1.0, longitude = 1.0)
        val favoriteLocation1 = FavoriteLocation(name = "Test city 1", latitude = 0.0, longitude = 0.0)
        val favoriteLocation2 = FavoriteLocation(name = "Test city 2", latitude = 1.0, longitude = 1.0)
        whenever(chatMapper.toDto(chat)).thenReturn(chatEntity)
        whenever(favoriteLocationH2Repository.findByChat(chatEntity)).thenReturn(listOf(favoriteLocationEntity1, favoriteLocationEntity2))
        whenever(favoriteLocationMapper.toModel(favoriteLocationEntity1)).thenReturn(favoriteLocation1)
        whenever(favoriteLocationMapper.toModel(favoriteLocationEntity2)).thenReturn(favoriteLocation2)

        val favoriteLocations = sut.getFavoriteLocations(chat)

        assertEquals(2, favoriteLocations.size)
        assertTrue(favoriteLocations.contains(favoriteLocation1))
        assertTrue(favoriteLocations.contains(favoriteLocation2))
        verify(chatMapper).toDto(chat)
        verify(favoriteLocationH2Repository).findByChat(chatEntity)
        verify(favoriteLocationMapper).toModel(favoriteLocationEntity1)
        verify(favoriteLocationMapper).toModel(favoriteLocationEntity2)
    }

    @Test
    fun `getting favorite locations from a chat without any should return an empty list`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(1, "user_id")
        whenever(chatMapper.toDto(chat)).thenReturn(chatEntity)
        whenever(favoriteLocationH2Repository.findByChat(chatEntity)).thenReturn(emptyList())

        val favoriteLocations = sut.getFavoriteLocations(chat)

        assertTrue(favoriteLocations.isEmpty())
        verify(chatMapper).toDto(chat)
        verify(favoriteLocationH2Repository).findByChat(chatEntity)
    }


    @Test
    fun `add alert to chat with favorite locations`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(1, "user_id")
        val sidneyEntity = FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        whenever(chatH2Repository.findById(chat.id)).thenReturn(Optional.of(chatEntity))
        whenever(favoriteLocationH2Repository.findByChat(chatEntity)).thenReturn(listOf(sidneyEntity))

        sut.addAlert(chat, 12)

        verify(chatH2Repository).findById(chat.id)
        verify(favoriteLocationH2Repository).findByChat(chatEntity)
        verify(alertH2Repository).save(
            argThat { alertEntity ->
                alertEntity.hourOfDay == 12 && alertEntity.chat?.id == chatEntity.id
            }
        )
    }

    @Test
    fun `adding alert to chat without favorite locations should throw IllegalArgumentException`() {
        val chat = Chat(id = 1)

        assertThrows<IllegalArgumentException> { sut.addAlert(chat, 12) }

        verify(chatH2Repository).findById(chat.id)
    }

    @Test
    fun `adding alert to non-existent chat should throw IllegalArgumentException`() {
        val chat = Chat(id = 1)
        val chatEntity = ChatEntity(1, "user_id")
        FavoriteLocationEntity(
            id = 1,
            name = "Sidney",
            latitude = 37.7749,
            longitude = -122.4194,
        )
        whenever(chatH2Repository.findById(chat.id)).thenReturn(Optional.of(chatEntity))
        whenever(favoriteLocationH2Repository.findByChat(chatEntity)).thenReturn(emptyList())

        assertThrows<IllegalArgumentException> { sut.addAlert(chat, 12) }

        verify(chatH2Repository).findById(chat.id)
        verify(favoriteLocationH2Repository).findByChat(chatEntity)
    }


    @Test
    fun `test getAlerts returns empty list when no alerts found`() {
        val hourOfDay = 12
        whenever(alertH2Repository.findByHourOfDay(hourOfDay)).thenReturn(emptyList())

        val result = sut.getAlerts(hourOfDay)

        assertTrue(result.isEmpty())
        verify(alertH2Repository).findByHourOfDay(hourOfDay)
    }

    @Test
    fun `test getAlerts returns list of chats when alerts found`() {
        val hourOfDayAt12 = 12
        val chatEntity1 = ChatEntity(id = 1)
        val chatEntity2 = ChatEntity(id = 2)
        val chat1 = Chat(id = 1)
        val chat2 = Chat(id = 2)
        val alertEntity1 = AlertEntity().apply {
            id = 1
            hourOfDay = hourOfDayAt12
            chat = chatEntity1
        }
        val alertEntity2 = AlertEntity().apply {
            id = 2
            hourOfDay = hourOfDayAt12
            chat = chatEntity2
        }
        whenever(alertH2Repository.findByHourOfDay(12)).thenReturn(listOf(alertEntity1, alertEntity2))
        whenever(chatMapper.toModel(chatEntity1)).thenReturn(chat1)
        whenever(chatMapper.toModel(chatEntity2)).thenReturn(chat2)

        val result = sut.getAlerts(hourOfDayAt12)

        assertTrue(result.containsAll(listOf(chat1, chat2)))
        verify(alertH2Repository).findByHourOfDay(12)
        verify(chatMapper).toModel(chatEntity1)
        verify(chatMapper).toModel(chatEntity2)
    }


    @Test
    fun `deleteAlert should call repository with expected parameter`() {
        val chat = Chat(id = 1)
        val hourOfDay = 12

        sut.deleteAlert(chat, hourOfDay)
        // Then
        verify(alertH2Repository).deleteByChatIdAndHourOfDay(chatId = chat.id, hourOfDay = hourOfDay)
    }

}
