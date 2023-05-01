package me.fernando.chat.db.mapper

import me.fernando.chat.db.entities.FavoriteLocationEntity
import me.fernando.chat.domain.FavoriteLocation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FavoriteLocationMapperTest {

    private lateinit var sut: FavoriteLocationMapper

    @BeforeEach
    fun setUp() {
        sut = FavoriteLocationMapper()
    }

    @Test
    fun `toDto maps FavoriteLocation to FavoriteLocationEntity`() {
        val location = FavoriteLocation(
            id = 1,
            name = "New York",
            latitude = 40.7128,
            longitude = -74.0060,
            country = "USA"
        )
        val expectedEntity = FavoriteLocationEntity(
            id = 1,
            name = "New York",
            latitude = 40.7128,
            longitude = -74.0060,
            country = "USA"
        )

        val actualEntity = sut.toDto(location)

        assertEquals(expectedEntity, actualEntity)
    }

    @Test
    fun `toModel maps FavoriteLocationEntity to FavoriteLocation`() {
        val entity = FavoriteLocationEntity(
            id = 1,
            name = "New York",
            latitude = 40.7128,
            longitude = -74.0060,
            country = "USA"
        )
        val expectedLocation = FavoriteLocation(
            id = 1,
            name = "New York",
            latitude = 40.7128,
            longitude = -74.0060,
            country = "USA"
        )

        val actualLocation = sut.toModel(entity)

        assertEquals(expectedLocation, actualLocation)
    }
}
